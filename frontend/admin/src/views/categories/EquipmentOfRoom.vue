<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.key" placeholder="Name" style="width: 500px;padding-right:20px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="fas fa-search" @click="handleFilter">
        Search
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="fas fa-edit" @click="handleCreate">
        Add
      </el-button>
    </div>

    <el-table
      v-loading="listLoading"
      :data="equipmentOfRooms"
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
      <el-table-column label="Equipment" align="center">
        <template slot-scope="{row}">
          <span>{{ row.equipment.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Amount" align="center">
        <template slot-scope="{row}">
          <span>{{ row.amount }}</span>
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

      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width" width="200px">
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" center width="40%">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="150px">
        <el-form-item v-show="dialogStatus === 'update'" label="Id" prop="id">
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>

        <el-form-item label="Equipment" prop="equipment">
          <el-select v-model="temp.equipment.id" style="width: 100%;">
            <el-option v-for="(type, idx) in equipments" :key="idx" :value="type.id">
              {{ type.name }}
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="Amount" prop="amount">
          <el-input v-model="temp.amount" type="number" />
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
  </div>
</template>

<script>
import { getEquipments, createEquipment, updateEquipment, deleteEquipment, listEquipments } from '@/api/equipmentOfRoom'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const deleteOptions = [
  { key: 1, display_name: 'True' },
  { key: 0, display_name: 'False' }
]

export default {
  name: 'EquipmentOfRoom',
  components: { Pagination },
  directives: { waves },
  row: ['1', '2', '3', '4', '5'],
  props: ['rid'],
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
    const checkEquipment = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('Hãy chọn loại thiết bị'))
      }
    }
    return {
      dialogConfirmDeleteVisible: false,
      dialogConfirmRestoreVisible: false,
      centerDialogVisible: false,
      equipmentOfRooms: [],
      equipments: [],
      roomIds: '',
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
        equipment: {
          id: ''
        },
        amount: '',
        createAt: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        equipment: [
          { required: true, validator: checkEquipment, trigger: 'change' }
        ],
        amount: [
          { required: true, validator: checkAmount, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = false
      this.roomIds = this.rid
      getEquipments(this.roomIds).then(response => {
        this.equipmentOfRooms = response.data.equipmentOfRooms
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0 * 1000)
      })
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        equipment: {
          id: ''
        },
        amount: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      listEquipments().then(response => {
        this.equipments = response.data.equipments
      })
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      console.log('ahịeifjaha')
      try {
        this.$refs['dataForm'].validate((valid) => {
          console.log('ahaaha')
          if (valid) {
            console.log('ahihi')
            createEquipment(this.roomIds, this.temp).then(response => {
              this.equipmentOfRooms = response.data.equipmentOfRooms
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
      } catch (e) {
        console.log('1', e)
      }
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      listEquipments().then(response => {
        this.equipments = response.data.equipments
      })
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
          updateEquipment(this.roomIds, tempData).then(response => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
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
    handleDelete(row) {
      this.temp = Object.assign({}, row)
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    deleteData(id) {
      this.centerDialogVisible = false
      deleteEquipment(id).then(response => {
        this.$notify({
          title: 'Success',
          message: 'Delete Successfully',
          type: 'success',
          duration: 2000
        })
      })
      getEquipments(this.roomIds).then(response => {
        this.equipmentOfRooms = response.data.equipmentOfRooms
      })
    },
    formatJson(filterVal) {
      return this.equipmentOfRooms.map(v => filterVal.map(j => {
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
    }
  }
}
</script>

