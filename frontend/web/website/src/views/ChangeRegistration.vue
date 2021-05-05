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
                  <form role="form" class="text-center border border-light p-5" >

                    <p class="h4 mb-4">Chỉnh sửa đăng kí</p>

                    <div class="row">
                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Loại phòng</label>
                          </div>
                          <div class="col-md-7">
                            <select v-model="roomType" class="browser-default custom-select mb-4" :onChange="handleSelectRoom()">
                              <option v-for="(type, idx) in roomTypeOptions" :key="idx" :value="type.key">
                                {{type.display_name}}
                              </option>
                            </select>
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
                      </div>                      
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-3">
                            <label>Thiết bị</label>
                          </div>
                          <div class="col-md-7">
                            <select :disabled="disabled" class="browser-default custom-select mb-4">
                          <option value="" disabled>Choose option</option>
                          <option value="1" selected>Máy hàn</option>
                          <option value="2">Cưa</option>
                          <option value="3">Bút điện</option>
                          <option value="4">Dây cắm</option>
                        </select>
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
                      </div>                      
                    </div>

                    <div class="row">
                      <div class="col">
                        <div class="form-group">
                          <label>Ngày sử dụng:</label>
                          <input class="form-control"  v-model="date" type="date">
                        </div>
                      </div>

                      <div class="col">
                        <div class="form-group">
                          <label>Thời gian bắt đầu:</label>
                          <select class="browser-default custom-select mb-4" v-model="startTime" :onChange="handleSelectRoom()">
                            <option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{type.display_name}}</option>
                          </select>
                        </div>
                      </div>


                      <div class="col">
                        <div class="form-group">
                          <label>Thời gian kết thúc:</label>
                          <select class="browser-default custom-select mb-4" v-model="endTime" :onChange="handleSelectRoom()">
                            <option v-for="(type, idx) in timeOptions" :key="idx" :value="type.key">{{type.display_name}}</option>
                          </select>
                        </div>
                      </div>
                    </div>


                    <!-- Send button -->
                    <button class="btn btn-info btn-block" type="submit">Save</button>

                  </form>

                </card>
            </div>
        </section>
    </div>
</template>
<script>

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
  name: "ChangeRegistration",
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
      groupSize: 1
    }
  },
  created(){

  },
  methods:{
    handleSelectRoom(){
      if(this.roomType === 2){
        this.disabled = false
      }else{
        this.disabled = true
      }
    }
  }
};

</script>
<style>

</style>
