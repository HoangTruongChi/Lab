<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="search" placeholder="Name" style="width: 500px;padding-right:20px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="fas fa-search" @click="handleFilter">
        Search
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="fas fa-edit" @click="handleCreate">
        Add
      </el-button>
    </div>
    <!-- fit
    highlight-current-row
    -->
    <el-table
      v-loading="listLoading"
      :data="rooms.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      border
      style="width: 100%;"
    >
      <el-table-column label="ID" prop="id" align="center" width="70">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Name" align="center" width="110">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Code" align="center" width="110">
        <template slot-scope="{row}">
          <span>{{ row.code }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Type" align="center" width="100">
        <template slot-scope="{row}">
          <span v-if="row.roomType == 1">Coworking</span>
          <span v-if="row.roomType == 2">Laboratory</span>
        </template>
      </el-table-column>
      <el-table-column label="Open time" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.openTime }}h</span>
        </template>
      </el-table-column>
      <el-table-column label="Close time" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.closeTime }}h</span>
        </template>
      </el-table-column>
      <el-table-column label="Open day" align="center" width="110">
        <template slot-scope="{row}">
          <span v-if="row.openDay == 8">Chủ Nhật</span>
          <span v-if="row.openDay != 8">Thứ {{ row.openDay }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Close day " align="center" width="110">
        <template slot-scope="{row}">
          <span v-if="row.closeDay == 8">Chủ Nhật</span>
          <span v-if="row.closeDay != 8">Thứ {{ row.closeDay }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Create At" align="center" width="110">
        <template slot-scope="{row}">
          <span>{{ (new Date(parseInt(row.createdAt,10))).toLocaleDateString() }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Update At" align="center" width="110">
        <template slot-scope="{row}">
          <span>{{ (new Date(parseInt(row.updatedAt,10))).toLocaleDateString() }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Max People" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.maxPeople }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Equipment" align="center" width="110">
        <template v-if="row.roomType == 1" slot-scope="{row}">
          <router-link :to="{name:'EquipmentOfRoom', params :{rid : row.id}}">
            <el-button :disabled="true" type="warning" size="mini" style="margin-right: 10px">
              View
            </el-button>
          </router-link>
        </template>
        <template v-if="row.roomType == 2" slot-scope="{row}">
          <router-link :to="{name:'EquipmentOfRoom', params :{rid : row.id}}">
            <el-button :disabled="false" type="warning" size="mini" style="margin-right: 10px">
              View
            </el-button>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column label="Actions" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button v-if="!row.delete" type="primary" size="mini" style="margin-right: 10px" @click="handleUpdate(row)">
            Edit
          </el-button>

          <el-button v-if="!row.delete" type="danger" size="mini" style="margin-right: 10px" @click="handleDelete(row)">
            delete
          </el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center width="40%">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="200px">
        <el-form-item label="Name" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>

        <el-form-item label="Code" prop="code">
          <el-input v-model="temp.code" />
        </el-form-item>

        <el-form-item v-show="dialogStatus == 'create'" label="Type" prop="roomType">
          <el-select v-model="temp.roomType" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in roomTypeOptions" :key="idx" :value="type.key">
              {{ type.display_name }}
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item v-show="dialogStatus == 'update'" label="Type" prop="roomType">
          <el-select v-model="temp.roomType" :disabled="true" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in roomTypeOptions" :key="idx" :value="type.key">
              {{ type.display_name }}
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Open time" prop="openTime">
          <el-select v-model="temp.openTime" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{ type.display_name }}</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Close time" prop="closeTime">
          <el-select v-model="temp.closeTime" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{ type.display_name }}</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Open day of week" prop="openDay">
          <el-select v-model="temp.openDay" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in dayOptions" :key="idx" :value="type.key">{{ type.display_name }}</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Close day of week" prop="closeDay">
          <el-select v-model="temp.closeDay" style="width: 100%;" :on-change="handleSelectRoom()">
            <el-option v-for="(type, idx) in dayOptions" :key="idx" :value="type.key">{{ type.display_name }}</el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Max People" prop="maxPeople">
          <el-input v-model="temp.maxPeople" type="number" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="dialogStatus=='create'?createData():updateData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>

    <!-- dialog delete -->
    <el-dialog
      title="Warning"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" />
      <span>Are you sure to delete this?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Cancel</el-button>
        <el-button type="danger" @click="deleteData(temp.id)">Delete</el-button>
      </span>
    </el-dialog>

    <div class="filter-container" style="margin: 10px">
      <el-button class="filter-item" style="margin-left: 10px;float: right" @click="nextPage">
        Nexts
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;float: right" @click="prevPage">
        Previous
      </el-button>
    </div>
  </div>
</template>

<script>
import { fetchRooms, createRoom, updateRoom, deleteRoom, getEquipments } from '@/api/room'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
/* import {getEquipments} from "../../../../web/website/src/api/equipments";*/ // secondary package based on el-pagination

const deleteOptions = [
  { key: 1, display_name: 'True' },
  { key: 0, display_name: 'False' }

]

const roomTypeOptions = [
  { key: 1, display_name: 'Coworking space' },
  { key: 2, display_name: 'Practical Laboratory' }
]

const timeOptions = [
  { key: 6, display_name: '6h' },
  { key: 7, display_name: '7h' },
  { key: 8, display_name: '8h' },
  { key: 9, display_name: '9h' },
  { key: 10, display_name: '10h' },
  { key: 11, display_name: '11h' },
  { key: 12, display_name: '12h' },
  { key: 13, display_name: '13h' },
  { key: 14, display_name: '14h' },
  { key: 15, display_name: '15h' },
  { key: 16, display_name: '16h' },
  { key: 17, display_name: '17h' },
  { key: 18, display_name: '18h' }
]

const dayOptions = [
  { key: 2, display_name: 'Thứ 2' },
  { key: 3, display_name: 'Thứ 3' },
  { key: 4, display_name: 'Thứ 4' },
  { key: 5, display_name: 'Thứ 5' },
  { key: 6, display_name: 'Thứ 6' },
  { key: 7, display_name: 'Thứ 7' },
  { key: 8, display_name: 'Chủ nhật' }

]

export default {
  name: 'Room',
  components: { Pagination },
  directives: { waves },
  row: ['1', '2', '3', '4', '5', '6', '7', '8', '9'],

  data() {
    var checkMaxPeople = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Hãy nhập giá trị max people'))
      }
      setTimeout(() => {
        if (value < 0) {
          callback(new Error('Max People > 0'))
        } else {
          callback()
        }
      }, 1000)
    }
    /* var checkOpenDay = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Hãy chọn giá trị Open Day'));
      }
    };*/
    var checkOpenTime = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Hãy chọn giá trị Open Time'))
      }
      setTimeout(() => {
        if (value === 18) {
          callback(new Error('Giờ mở cửa không hợp lệ'))
        } else {
          callback()
        }
      }, 1000)
    }
    var checkCloseTime = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Hãy chọn giá trị Close Time'))
      }
      setTimeout(() => {
        if (value <= this.temp.openTime) {
          callback(new Error('Giờ đóng cửa không hợp lệ!'))
        } else {
          callback()
        }
      }, 1000)
    }

    return {
      dialogConfirmDeleteVisible: false,
      dialogConfirmRestoreVisible: false,
      centerDialogVisible: false,
      rooms: [],
      dataRooms: [],
      equipments: [],
      roomTypeOptions,
      timeOptions,
      dayOptions,
      roomType: 1,
      search: '',
      equip: undefined,
      total: 0,
      listLoading: true,
      equipmentLoading: true,
      listQuery: {
        deleted: 0,
        page: 1,
        limit: 10,
        key: null
      },
      deleteOptions,
      showDelete: false,
      temp: {
        id: undefined,
        name: '',
        code: '',
        roomType: '',
        openTime: '',
        closeTime: '',
        openDay: '',
        closeDay: '',
        maxPeople: ''
      },
      dialogFormVisible: false,
      dialogVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        equipment: 'Equipment',
        create: 'Create'
      },
      rules: {
        name: [
          { required: true, message: 'Tên phòng không được bỏ trống', trigger: 'blur' }
        ],
        code: [
          { required: true, message: 'Mã phòng không được bỏ trống', trigger: 'blur' }
        ],
        roomType: [
          { required: true, message: 'Hãy chọn loại phòng', trigger: 'change' }
        ],
        openTime: [
          { required: true, validator: checkOpenTime, trigger: 'change' }
        ],
        openDay: [
          { required: true, message: 'Hãy chọn ngày đóng cửa', trigger: 'change' }
        ],
        closeTime: [
          { required: true, validator: checkCloseTime, trigger: 'change' }
        ],
        closeDay: [
          { required: true, message: 'Hãy chọn loại phòng', trigger: 'change' }
        ],
        maxPeople: [
          { required: true, validator: checkMaxPeople, trigger: 'blur' }
        ]
      },
      currentSortDir: 'asc',
      currentSort: 'name',
      pageSize: 10,
      currentPage: 1
    }
  },

  computed: {
    changePage: function() {
      return this.rooms.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    }
  },
  created() {
    this.getList()
  },

  methods: {
    getList() {
      this.listLoading = false
      fetchRooms().then(response => {
        this.rooms = response.data.rooms.slice(0, 10)
        this.dataRooms = response.data.rooms
        /* this.total = response.data.total*/
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0 * 1000)
      })
    },
    equipment(row) {
      this.equipmentLoading = false
      getEquipments(row.id).then(response => {
        this.equipments = response.data.equipments
      })
      this.dialogStatus = 'equipment'
      this.dialogVisible = true
      /* this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })*/
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        code: '',
        roomType: '',
        openTime: '',
        closeTime: '',
        openDay: '',
        closeDay: '',
        maxPeople: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createRoom(this.temp).then(response => {
            this.rooms = response.data.rooms.slice(0, 10)
            this.dataRooms = response.data.rooms
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
            this.getList()
          })
        } else {
          console.log('Not valid')
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateRoom(tempData.id, tempData).then(response => {
            this.rooms = response.data.rooms.slice(0, 10)
            this.dataRooms = response.data.rooms
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        } else {
          console.log('Not valid')
        }
      })
    },
    handleDelete(row) {
      this.temp = Object.assign({}, row)
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    deleteData(roomId) {
      this.centerDialogVisible = false
      deleteRoom(roomId).then(response => {
        this.rooms = response.data.rooms
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    viewDelete: function() {
      this.listQuery.deleted = this.showDelete ? 1 : 0
      this.getList()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleSelectRoom() {
      if (this.roomType === 2) {
        this.disabled = false
      } else {
        this.disabled = true
      }
    },
    sort: function(s) {
      // if s == current sort, reverse
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc'
      }
      this.currentSort = s
    },
    nextPage: function() {
      if ((this.currentPage * this.pageSize) < this.dataRooms.length) this.currentPage++

      this.rooms = this.dataRooms.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    },
    prevPage: function() {
      if (this.currentPage > 1) this.currentPage--

      this.rooms = this.dataRooms.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    }
  }

}
</script>
