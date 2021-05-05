<template>
  <div class="app-container">
    <div class="container">

      <el-form ref="dataForm" :inline="true" :rules="rules" :model="temp" label-position="left">
        <el-form-item label="Phòng">
          <el-input v-model="search" placeholder="Tìm theo tên phòng" class="filter-item" @keyup.enter.native="handleFilter" />
        </el-form-item>
        <!--<el-form-item label="Phòng:">
          <el-select v-model="temp.roomId" >
            <el-option v-for="(type, idx) in rooms" :key="idx" :value="type.id">
              {{ type.name }}
            </el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="Ngày đăng kí">
          <el-input v-model="temp.date" type="date" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="fas fa-search" @click="searchHistory()">
            Search
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="float: right" type="success" icon="fas fa-sync" @click="reloadHistory()">
            Reload
          </el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      v-loading="listLoading"
      :data="roomDivisionResultsList.filter(data => !search || data.room.name.toLowerCase().includes(search.toLowerCase()))"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="Id" prop="id" align="center">
        <template slot-scope="{row}">
          <span>{{ row.roomRegisId }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Status" align="center">
        <template slot-scope="{row}">
          <span v-if="row.status == -1"> Đã hủy </span>
          <span v-if="row.status == 0"> Thất bại </span>
          <span v-if="row.status == 1"> Thành công </span>
          <span v-if="row.status == 2"> Đang xử lý </span>
        </template>
      </el-table-column>

      <el-table-column label="Group Size" align="center">
        <template slot-scope="{row}">
          <span>{{ row.groupSize }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Room" align="center">
        <template slot-scope="{row}">
          <span>{{ row.room.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Date" align="center">
        <template slot-scope="{row}">
          <span>{{ (new Date(parseInt(row.date,10))).toLocaleDateString() }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Start time" align="center">
        <template slot-scope="{row}">
          <span>{{ row.startTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="End time" align="center">
        <template slot-scope="{row}">
          <span>{{ row.endTime }}</span>
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
      <el-table-column label="User" align="center">
        <template slot-scope="{row}">
          <el-button type="warning" size="mini" @click="userOfRoom(row)">User</el-button>
        </template>
      </el-table-column>
      <el-table-column label="Equipment" align="center">
        <template slot-scope="{row}">
          <el-button type="success" size="mini" @click="equipmentDivision(row)">Equipment</el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!--dialog equipment-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogVisible" center>
      <el-table
        v-loading="equipmentLoading"
        :data="equipmentDivisionResults"
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
            <span>{{ row.equipment.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Amount" align="center">
          <template slot-scope="{row}">
            <span>{{ row.amount }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Status" align="center">
          <template slot-scope="{row}">
            <span v-if="row.status == -1"> Đã hủy </span>
            <span v-if="row.status == 0"> Thất bại </span>
            <span v-if="row.status == 1"> Thành công </span>
            <span v-if="row.status == 2"> Đang xử lý </span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!--dialog user of room-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogUserVisible" center>
      <el-table
        v-loading="userLoading"
        :data="users"
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
        <el-table-column label="Email" align="center">
          <template slot-scope="{row}">
            <span>{{ row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Role" align="center">
          <template slot-scope="{row}">
            <span>{{ row.role }}</span>
          </template>
        </el-table-column>
        <el-table-column label="Status" align="center">
          <template slot-scope="{row}">
            <span v-if="row.status == -1"> Đã bị xóa </span>
            <span v-if="row.status == 0"> Thất bại </span>
            <span v-if="row.status == 1"> Đang hoạt động </span>
          </template>
        </el-table-column>
      </el-table>
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
import { fetchRoomDivisions, getEquipmentDivisions, getUser, getRooms } from '@/api/history'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
/* import {getEquipments} from "../../../../web/website/src/api/equipments";*/ // secondary package based on el-pagination

const deleteOptions = [
  { key: 1, display_name: 'True' },
  { key: 0, display_name: 'False' }

]

export default {
  name: 'History',
  components: { Pagination },
  directives: { waves },
  row: ['1', '2', '3', '4', '5', '6', '7', '8', '9'],
  data() {
    return {
      dialogConfirmDeleteVisible: false,
      dialogConfirmRestoreVisible: false,
      centerDialogVisible: false,
      roomDivisionResultsList: [],
      dataroomDivisionResultsList: [],
      equipmentDivisionResults: [],
      users: [],
      rooms: [],
      roomType: 1,
      search: '',
      equip: undefined,
      total: 0,
      listLoading: true,
      equipmentLoading: true,
      userLoading: true,
      listQuery: {
        deleted: 0,
        page: 1,
        limit: 10,
        key: null
      },
      deleteOptions,
      showDelete: false,
      temp: {
        date: ''
      },
      dialogUserVisible: false,
      dialogVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        equipment: 'Equipment',
        user: 'User'
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
      fetchRoomDivisions().then(response => {
        this.roomDivisionResultsList = response.data.roomDivisionResultsList.slice(0, 10)
        this.dataroomDivisionResultsList = response.data.roomDivisionResultsList
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 0 * 1000)
      })
      getRooms().then(response => {
        this.rooms = response.data.rooms
      })
    },
    equipmentDivision(row) {
      this.equipmentLoading = false
      getEquipmentDivisions(row.roomRegisId).then(response => {
        this.equipmentDivisionResults = response.data.equipmentDivisionResults
      })
      this.dialogStatus = 'equipment'
      this.dialogVisible = true
    },
    userOfRoom(row) {
      this.userLoading = false
      getUser(row.roomRegisId).then(response => {
        this.users = response.data.users
      })
      this.dialogStatus = 'user'
      this.dialogUserVisible = true
    },
    searchHistory() {
      this.listLoading = false
      fetchRoomDivisions(this.temp.date).then(response => {
        this.roomDivisionResultsList = response.data.roomDivisionResultsList.slice(0, 10)
        this.dataroomDivisionResultsList = response.data.roomDivisionResultsList
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    reloadHistory() {
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
      if ((this.currentPage * this.pageSize) < this.dataroomDivisionResultsList.length) this.currentPage++

      this.roomDivisionResultsList = this.dataroomDivisionResultsList.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    },
    prevPage: function() {
      if (this.currentPage > 1) this.currentPage--

      this.roomDivisionResultsList = this.dataroomDivisionResultsList.filter((row, index) => {
        const start = (this.currentPage - 1) * this.pageSize
        const end = this.currentPage * this.pageSize
        if (index >= start && index < end) return true
      })
    }
  }
}
</script>
