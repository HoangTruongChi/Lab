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

                  <p class="h4 mb-4">Kết quả đăng ký</p>

                  
                  <div class="row"></div>

                  <div class="row">
                    <div class="col-2">
                      <div class="row">
                        <!-- id -->
                        <p>ID</p>
                      </div>

                      <div class="row">
                        <!-- Type -->
                        <p>Phòng</p>  
                      </div>

                      <div class="row">
                        <!-- equipment -->
                        <p>Thiết bị</p>
                      </div>

                      <div class="row">
                        <!-- amount equipment -->
                        <p>Số lượng thiết bị</p>
                      </div>

                      <div class="row">
                        <p>Ngày sử dụng</p>
                      </div>

                      <div class="row">
                        <p>Thời gian bắt đầu</p>
                      </div>

                      <div class="row">
                        <p>Thời gian kết thúc</p>
                      </div>

                      <!-- Message -->
                      <div class="row">
                        <p>Message</p>
                      </div>
                    </div>

                    <div class="col-6">
                      <div class="row">
                        <!-- id -->
                        <p>: {{ id }}</p>
                      </div>

                      <div class="row">
                        <!-- Type -->
                        <p>: {{ room }}</p>  
                      </div>

                      <div class="row">
                        <!-- equipment -->
                        <p>: {{ equipment }}</p>
                      </div>

                      <div class="row">
                        <!-- amount equipment -->
                        <p>: {{ amountEquipment }}</p>
                      </div>

                      <div class="row">
                        <p>: {{ date }}</p>
                      </div>

                      <div class="row">
                        <p>: {{ startTime }}</p>
                      </div>

                      <div class="row">
                        <p>: {{ endTime }}</p>
                      </div>

                      <!-- Message -->
                      <div class="row">
                        <p>: {{ message }}</p>
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <!-- Chane button -->
                    <button class="btn btn-primary btn-block" type="submit">Exit</button>
                  </div>
                    
                </form>
                <!-- Default form contact -->

              </card>
          </div>
      </section>
  </div>
</template>
<script>
export default {
};
</script>
<style>
</style>
