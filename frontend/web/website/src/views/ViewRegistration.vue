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

                  <p class="h4 mb-4">Phòng Lab đã đăng ký</p>
                
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Ngày đăng ký</th>
                        <th scope="col">Trạng thái đăng ký</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">1</th>
                        <td>{{ room }}</td>
                        <td>{{ status }}</td>
                        <td>
                          <button class="btn btn-primary btn-sm" type="submit">Chỉnh sửa</button>
                          <button class="btn btn-danger btn-sm" type="submit">Hủy bỏ</button>
                          <button class="btn btn-success btn-sm" type="submit">Xem kết quả</button>
                        </td>
                      </tr>
                      <tr>
                        <th scope="row">2</th>
                        <td>{{ room }}</td>
                        <td>{{ status }}</td>
                        <td>
                          <button class="btn btn-primary btn-sm" type="submit">Chỉnh sửa</button>
                          <button class="btn btn-danger btn-sm" type="submit">Hủy bỏ</button>
                          <button class="btn btn-success btn-sm" type="submit">Xem kết quả</button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                
                    
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

