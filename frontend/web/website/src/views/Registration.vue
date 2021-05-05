<template>
    <div class="profile-page">
        <section class="section-profile-cover section-shaped my-0">
            <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </section>
        <section class="section section-skew">
            <div class="container">
                <card shadow class="card-profile mt--300" no-body>
                  <form role="form" class="text-center border border-light p-5" 
                    @submit.prevent="register"
                  >

                    <p class="h4 mb-4">Đăng kí sử dụng phòng Lab</p>

                    <div class="row">
                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Loại phòng</label>
                          </div>
                          <div class="col-md-9">
                            <select v-model="roomType" class="browser-default custom-select mb-4" :onChange="handleSelectRoom()">
                              <option v-for="(type, idx) in roomTypeOptions" :key="idx" :value="type.key">
                                {{type.display_name}}
                              </option>
                            </select>
                          </div>
                        </div>
                        <div class="row" style="margin-top:-20px;display:block;color:red;" :hidden="roomTypeValid">
                          <div class="col-md-3">
                          </div>
                          <div class="col-md-9" style="margin-left:53px">
                            Loại phòng không hợp lệ !
                          </div>
                        </div>
                      </div>

                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Số người</label>
                          </div>
                          <div class="col-md-9">
                            <input name="amount" v-model="groupSize"
                                    class="form-control mb-4"
                                    placeholder="Số lượng người">
                          </div>
                        </div>
                        <div class="row" style="margin-top:-20px;display:block;color:red;" :hidden="groupSizeValid">
                          <div class="col-md-3">
                          </div>
                          <div class="col-md-9" style="margin-left:45px">
                            Số người không hợp lệ !
                          </div>
                        </div>
                      </div>   
                                         
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Thiết bị</label>
                          </div>
                          <div class="col-md-7">
                            <select :disabled="disabled" class="browser-default custom-select mb-4" v-model="equipment">
                              <option v-for="(type, idx) in equipments" :key="idx" :value="type.id">
                                {{type.name}}
                              </option>
                            </select>
                          </div>
                        </div>
                        <div class="row" style="margin-top:-20px;display:block;color:red;" :hidden="equipmentValid">
                          <div class="col-md-3">
                          </div>
                          <div class="col-md-9" style="margin-left:40px">
                            Thiết bị không hợp lệ !
                          </div>
                        </div>
                      </div>

                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Số lượng</label>
                          </div>
                          <div class="col-md-9">
                            <input name="amountEquipment" :disabled="disabled" v-model="numOfEquip"
                                    class="form-control mb-4"
                                    placeholder="Số lượng thiết bị">
                          </div>
                        </div>
                        <div class="row" style="margin-top:-20px;display:block;color:red;" :hidden="numOfEquipValid">
                          <div class="col-md-3">
                          </div>
                          <div class="col-md-9" style="margin-left:70px">
                            Số lượng thiết bị không hợp lệ !
                          </div>
                        </div>               
                        </div>
                      </div>       
                      

                    <div class="row">
                      <div class="col-md-4">
                        <div class="form-group">
                          <label>Ngày sử dụng:</label>
                          <input class="form-control"  v-model="date" type="date">
                        </div>
                      </div>

                      <div class="col-md-4">
                        <div class="form-group">
                          <label>Thời gian bắt đầu:</label>
                          <select class="browser-default custom-select mb-4" v-model="startTime" :onChange="handleSelectRoom()">
                            <option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{type.display_name}}</option>
                          </select>
                        </div>
                      </div>


                      <div class="col-md-4">
                        <div class="form-group">
                          <label>Thời gian kết thúc:</label>
                          <select class="browser-default custom-select mb-4" v-model="endTime" :onChange="handleSelectRoom()">
                            <option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{type.display_name}}</option>
                          </select>
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-4" style="margin-top:-20px;display:block;color:red;margin-bottom:20px;" :hidden="dateValid">
                        Ngày sử dụng không hợp lệ !
                      </div>
                      <div class="col-md-4" style="margin-top:-20px;display:block;color:red;margin-bottom:20px;" :hidden="startTimeValid">
                        Thời gian bắt đầu không hợp lệ !
                      </div>
                      <div class="col-md-4" style="margin-top:-20px;display:block;color:red;margin-bottom:20px;" :hidden="endTimeValid">
                        Thời gian kết thúc không hợp lệ !
                      </div>
                    </div>


                    <!-- Send button -->
                    <button class="btn btn-info btn-block">Register</button>

                  </form>

                </card>
            </div>
        </section>
    </div>
</template>
<script>

import {getEquipments} from '../api/equipments';
import {register} from '../api/registration';

const roomTypeOptions = [
  {key: 1, display_name: 'Coworking space'},
  {key: 2, display_name: 'Practical Laboratory'}
]

const timeOptions = [
  {key: 6, display_name: '6h'},
  {key: 7, display_name: '7h'},
  {key: 8, display_name: '8h'},
  {key: 9, display_name: '9h'},
  {key: 10, display_name: '10h'},
  {key: 11, display_name: '11h'},
  {key: 12, display_name: '12h'},
  {key: 13, display_name: '13h'},
  {key: 14, display_name: '14h'},
  {key: 15, display_name: '15h'},
  {key: 16, display_name: '16h'},
  {key: 17, display_name: '17h'},
  {key: 18, display_name: '18h'},
]

export default {
  name: "Registration",
  data(){
    return {
      disabled: true,
      roomTypeOptions,
      timeOptions,
      startTime: 6,
      endTime: 7,
      roomType: 1,
      date: undefined,
      numOfEquip: 0,
      groupSize: 1,
      equipment: undefined,
      equipments: [],
      roomTypeValid: true,
      groupSizeValid: true,
      equipmentValid: true,
      numOfEquipValid: true,
      dateValid: true,
      startTimeValid: true,
      endTimeValid: true
    }
  },
  created(){
     getEquipments().then(resp => {
        this.equipments = resp.data.equiments
      })
  },
  methods:{
    handleSelectRoom(){
      if(this.roomType === 2){
        this.disabled = false
      }else{
        this.disabled = true
      }
    },
    valid(){
      let isValid = true
      
      if(this.roomType === undefined){
        isValid = false
        this.roomTypeValid = false
      }else{
        this.roomTypeValid = true
      }

      if(this.groupSize === undefined || this.groupSize < 1){
        isValid = false
        this.groupSizeValid = false
      }else{
        this.groupSizeValid = true
      }

      if(this.roomType === 2 && this.equipment === undefined){
        isValid = false
        this.equipmentValid = false
      }else{
        this.equipmentValid = true
      }

      if(this.roomType === 2 && (this.numOfEquip === undefined || this.numOfEquip < 1)){
        isValid = false
        this.numOfEquipValid = false
      }else{
        this.numOfEquipValid = true
      }

      if(this.date === undefined || !this.isDateValid(this.date)){
        isValid = false
        this.dateValid = false
      }else{
        this.dateValid = true
      }

      if(this.startTime === undefined){
        isValid = false
        this.startTimeValid = false
      }else{
        this.startTimeValid = true
      }

      if(this.endTime === undefined || this.startTime == undefined || this.startTime >= this.endTime){
        isValid = false
        this.endTimeValid = false
      }else{
        this.endTimeValid = true
      }

      return isValid
    },
    register(){
      if(this.valid()){
        let req = {
          roomType: this.roomType,
          groupSize: this.groupSize,
          equipmentId: this.equipment,
          numOfEquip: this.numOfEquip,
          date: this.date,
          startTime: this.startTime,
          endTime: this.endTime
        }

        register(req).then(resp => {
          alert(resp.msg)
          this.$router.go("/")
        })
      }
    },
    isDateValid(date) {
      return new Date(date) > new Date();
    }
  }
};

</script>
<style>

</style>
