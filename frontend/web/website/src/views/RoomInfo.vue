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

                  <p class="h4 mb-4">Thông tin các phòng</p>
                  

                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tên phòng</th>
                        <th scope="col">Loại phòng</th>
                        <th scope="col">Bắt đầu</th>
                        <th scope="col">Kết thúc</th>
                        <th scope="col">Ngày mở</th>
                        <th scope="col">Ngày nghỉ</th>
                        <th scope="col">Thiết bị</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row"></th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Chi tiết</button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thông tin thiết bị</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body">
                                <div class="row">
                                  <div class="col-2">
                                    <div class="row">
                                      <p>Mã thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Tên thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Số lượng</p>
                                    </div>
                                  </div>
                                  <div class="col-6">
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                              </div>
                            </div>
                          </div>
                        </div>
                        </td>
                      </tr>
                      <tr>
                        <th scope="row"></th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Chi tiết</button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thông tin thiết bị</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body">
                                <div class="row">
                                  <div class="col-2">
                                    <div class="row">
                                      <p>Mã thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Tên thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Số lượng</p>
                                    </div>
                                  </div>
                                  <div class="col-6">
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                              </div>
                            </div>
                          </div>
                        </div>
                        </td>
                      </tr>
                      <tr>
                        <th scope="row"></th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Chi tiết</button>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thông tin thiết bị</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body">
                                <div class="row">
                                  <div class="col-2">
                                    <div class="row">
                                      <p>Mã thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Tên thiết bị</p>
                                    </div>
                                    <div class="row">
                                      <p>Số lượng</p>
                                    </div>
                                  </div>
                                  <div class="col-6">
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                    <div class="row">
                                      <p>: </p>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                              </div>
                            </div>
                          </div>
                        </div>
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
}
</script>
<style >
</style>
