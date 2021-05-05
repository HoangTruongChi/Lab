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

    <el-table
      v-loading="listLoading"
      :data="equipments.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="ID" prop="id" align="center">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Name" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Total Amount" align="center">
        <template slot-scope="{row}">
          <span>{{ row.totalAmount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Description" align="center">
        <template slot-scope="{row}">
          <span>{{ row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Create At" align="center">
        <template slot-scope="{row}">
          <span>{{ (new Date(parseInt(row.createdAt,10))).toLocaleDateString() }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Update At" align="center">
        <template slot-scope="{row}">
          <span>{{ (new Date(parseInt(row.updatedAt,10))).toLocaleDateString() }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Actions" align="center" width="250px" class-name="small-padding fixed-width">
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

    <!-- dialog create update -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center>
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="180px" style="width: 400px; margin-left:50px;">
        <el-form-item v-show="dialogStatus === 'update'" label="Id" prop="id">
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>

        <el-form-item label="Name" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>

        <el-form-item label="Total Amount" prop="totalAmount">
          <el-input v-model="temp.totalAmount" type="number" />
        </el-form-item>

        <el-form-item label="Description" prop="description">
          <el-input v-model="temp.description" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
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
import { getEquipments, createEquipment, updateEquipment, deleteEquipment } from '@/api/equipment'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const deleteOptions = [
  { key: 1, display_name: 'True' },
  { key: 0, display_name: 'False' }
]

export default {
  name: 'Equipment',
  components: { Pagination },
  directives: { waves },
  row: ['1', '2', '3', '4', '5'],
  data() {
    var checkAmount = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('Hãy nhập số lượng thiết bị'))
      }
      setTimeout(() => {
        if (value < 0) {
          callback(new Error('Số thiết bị phải lớn hơn 0'))
        } else {
          callback()
        }
      }, 1000)
    }
    return {
      dialogConfirmDeleteVisible: false,
      dialogConfirmRestoreVisible: false,
      centerDialogVisible: false,
      equipmentId: '',
      equipments: [],
      dataEquipment: [],
      search: '',
      total: 0,
      listLoading: true,
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
        totalAmount: '',
        description: '',
        createAt: '',
        updateAt: '',
        delete: 0
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        name: [
          { required: true, message: 'Tên thiết bị không được bỏ trống', trigger: 'change' }
        ],
        totalAmount: [
          { required: true, validator: checkAmount, trigger: 'blur' }
        ]
      },
      currentSortDir: 'asc',
      currentSort: 'name',
      pageSize: 5,
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
      getEquipments(this.roomId).then(response => {
        this.equipments = response.data.equipments.slice(0, 5)
        this.dataEquipment = response.data.equipments
        this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0 * 1000)
      })
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        totalAmount: '',
        description: '',
        createAt: '',
        updateAt: '',
        delete: 0
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
          createEquipment(this.temp).then(response => {
            this.equipments = response.data.equipments
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
          updateEquipment(tempData.id, tempData).then(response => {
            this.equipments = response.data.equipments
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
    deleteData(equipmentId) {
      this.centerDialogVisible = false
      deleteEquipment(equipmentId).then(response => {
        this.equipments = response.data.equipments
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    viewDelete: function() {
      this.listQuery.deleted = this.showDelete ? 1 : 0
      this.getList()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    sort: function(s) {
      // if s == current sort, reverse
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc'
      }
      this.currentSort = s
    },
    nextPage: function() {
      if ((this.currentPage * this.pageSize) < this.dataEquipment.length) this.currentPage++

      this.equipments = this.dataEquipment.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    },
    prevPage: function() {
      if (this.currentPage > 1) this.currentPage--

      this.equipments = this.dataEquipment.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    }
  }
}
</script>

