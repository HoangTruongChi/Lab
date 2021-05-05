<template>
    <div>

        <div class="position-relative">
            <!-- shape Hero -->
            <section class="section-shaped my-0">
                <div class="shape shape-style-1 shape-default shape-skew">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <div class="container shape-container d-flex">
                    <div class="col px-0">
                        <div class="row">
                            <div class="col-lg-6">
                                <h1 class="display-3  text-white">LAB SYSTEM
                                    <span>Dễ dàng - Thuận lợi</span>
                                </h1>
                                <p class="lead  text-white">Hệ thống đăng kí sử dụng phòng Lab mong muốn hỗ trợ, giúp đỡ sinh viên tìm
                                được không gian phù hợp để học tập và nghiên cứu</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 1st Hero Variation -->
        </div>
        <section class="section section-lg pt-lg-0 mt--200">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-12">
                        <div class="row row-grid">
                            <div class="col-lg-6">
                                <card class="border-0" hover shadow body-classes="py-5">
                                    <icon name="ni ni-istanbul" type="success" rounded class="mb-4">
                                    </icon>
                                    <h6 class="text-success text-uppercase">CoWorking space</h6>
                                    <p class="description mt-3">Có chỗ ngồi cố định với không gian phòng riêng cùng các thành viên khác (có hạn chế số lượng),
                                      có hỗ trợ máy tính hiện đại</p>

                                    <div>
                                        <badge type="success" rounded>Coworking</badge>
                                        <badge type="success" rounded>Team</badge>
                                        <badge type="success" rounded>Pc</badge>
                                    </div>
                                    <base-button tag="a" href="#" type="success" class="mt-4">
                                        Register
                                    </base-button>
                                </card>
                            </div>
                            <div class="col-lg-6">
                                <card class="border-0" hover shadow body-classes="py-5">
                                    <icon name="ni ni-planet" type="warning" rounded class="mb-4">
                                    </icon>
                                    <h6 class="text-warning text-uppercase">practical laboratory</h6>
                                    <p class="description mt-3">Phòng Lab không chỉ có chỗ ngồi mà còn cung cấp dụng cụ thí nghiệm,
                                      thực hành cho sinh viên nghiên cứu, sáng tạo</p>
                                    <div>
                                        <badge type="warning" rounded>Equipment</badge>
                                        <badge type="warning" rounded>product</badge>
                                        <badge type="warning" rounded> laboratory</badge>
                                    </div>
                                    <base-button tag="a" href="#" type="warning" class="mt-4">
                                        Register
                                    </base-button>
                                </card>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="section section-lg">
            <div class="container">
                <div class="row row-grid align-items-center">
                    <div class="col-md-6 order-md-2">
                        <img src="img/theme/promo-1.png" class="img-fluid floating">
                    </div>
                    <div class="col-md-6 order-md-1">
                        <div class="pr-md-5">
                            <icon name="fa fa-snowflake-o" class="mb-5" size="lg" type="success" shadow
                                  rounded></icon>
                            <h3>PHÒNG LAB HIỆN ĐẠI</h3>
                            <p>Phòng Lab được thiết kế hiện đại, trẻ trung, yên tĩnh, giúp cho sinh viên có không gian học tập và nghiên
                            cứu tốt hơn.</p>
                            <ul class="list-unstyled mt-5">
                                <li class="py-2">
                                    <div class="d-flex align-items-center">
                                        <badge type="success" circle class="mr-3" icon="fa fa-users"></badge>
                                        <h6 class="mb-0">Common room: Không gian mở dùng để tổ chức sự kiện, có sức chứa khoảng 20-40 người.</h6>
                                    </div>
                                </li>
                                <li class="py-2">
                                    <div class="d-flex align-items-center">
                                        <badge type="success" circle class="mr-3" icon="fa fa-heart-o"></badge>
                                        <h6 class="mb-0">Private room: Văn phòng gồm những bàn làm việc có vách ngăn.</h6>
                                    </div>
                                </li>
                                <li class="py-2">
                                    <div class="d-flex align-items-center">
                                        <badge type="success" circle class="mr-3" icon="ni ni-satisfied"></badge>
                                        <h6 class="mb-0">Meeting room: Không gian riêng tư, có hỗ trợ bảng viết và máy chiếu.</h6>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="section bg-secondary">
            <div class="container">
                <div class="row row-grid align-items-center">
                    <div class="col-md-6">
                        <div class="card bg-default shadow border-0">
                            <img v-lazy="'img/theme/img1.png'" class="card-img-top">
                            <blockquote class="card-blockquote">
                                <svg preserveAspectRatio="none" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 583 95"
                                     class="svg-bg">
                                    <polygon points="0,52 583,95 0,95" class="fill-default"></polygon>
                                    <polygon points="0,42 583,95 683,0 0,95" opacity=".2" class="fill-default"></polygon>
                                </svg>
                                <h4 class="display-3 font-weight-bold text-white">TRANG THIẾT BỊ</h4>
                                <p class="lead text-italic text-white">Các phòng Lab được cung cấp các trang thiết bị hiện đại phục
                                vụ mục đích nghiên cứu, giải trí cho sinh viên. Bạn có thể cải thiện khả năng tập trung,
                                  nâng cao hiệu quả công việc và thỏa sức sáng tạo ở môi trường tràn đầy cảm hứng này.</p>
                            </blockquote>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="pl-md-5">
                            <icon name="fa fa-wpexplorer" class="mb-5" size="lg" type="warning" shadow rounded></icon>

                            <h3>CƠ SỞ VẬT CHẤT</h3>
                            <p class="lead">Các phòng Lab đều trang bị bàn ghế, máy chiếu phù hợp cho cho các mục đích sử dụng
                            như tổ chức sự kiện, các cuộc họp riêng tư, các buổi talk show,...</p>
                            <p>Cùng với đó là một số phòng đặc dụng có các trang thiết bị, máy móc tân tiến, hiện đại, hỗ trợ cho
                            quá trình nghiên cứu của sinh viên và giảng viên</p>
                            <p>Ngoài ra, còn có không gian mở, không gian thư giãn như khu party, máy pha cafe,... giành cho sinh viên và giảng viên sau những giờ làm việc,
                            học tập căng thẳng và mệt mỏi.</p>
                            <a href="https://soict.hust.edu.vn/" class="font-weight-bold text-warning mt-5">Thông tin chi tiết hơn ở Website của SOICT</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="section section section-shaped my-0 overflow-hidden">
            <div class="shape shape-style-1 bg-gradient-warning shape-skew">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div class="container py-0">
                <div class="row row-grid align-items-center">
                    <div class="col-md-6 order-lg-2 ml-lg-auto">
                        <div class="position-relative pl-md-5">
                            <img src="img/ill/ill-2.svg" class="img-center img-fluid">
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="d-flex px-3">
                            <div>
                                <icon name="ni ni-building" size="lg" class="bg-gradient-white" color="primary" shadow
                                      rounded></icon>
                            </div>
                            <div class="pl-4">
                                <h4 class="display-3 text-white">DỄ DÀNG ĐĂNG KÍ</h4>
                                <p class="text-white">Sử dụng phòng Lab là hoàn toàn miễn phí, và sinh viên có thể đăng kí sử dụng
                                một cách dễ dàng và thuận tiện.</p>
                            </div>
                        </div>
                        <card shadow class="shadow-lg--hover mt-5">
                            <div class="d-flex px-3">
                                <div>
                                    <icon name="ni ni-satisfied" gradient="success" color="white" shadow
                                          rounded></icon>
                                </div>
                                <div class="pl-4">
                                    <h5 class="title text-success">Lựa chọn không gian thích hợp</h5>
                                    <p>Sinh viên cần lựa chọn đúng không gian các phòng Lab phù hợp với
                                    mục đích và nguyện vọng của bản thân, cùng với đó là các phòng có trang thiết bị
                                    phục vụ cho quá trình học và nghiên cứu</p>
                                   <!-- <a href="#" class="text-success"></a>-->
                                </div>
                            </div>
                        </card>
                        <card shadow class="shadow-lg--hover mt-5">
                            <div class="d-flex px-3">
                                <div>
                                    <icon name="ni ni-active-40" gradient="warning" color="white" shadow
                                          rounded></icon>
                                </div>
                                <div class="pl-4">
                                    <h5 class="title text-warning">Đăng kí nhanh</h5>
                                    <p>Sau khi lựa chọn được không gian thì sinh viên điền đầy đủ thông tin để
                                    hệ thống có thể sắp xếp lịch cho các bạn một cách phù hợp</p>
                                    <!--<a href="#" class="text-warning">Learn more</a>-->
                                </div>
                            </div>
                        </card>
                    </div>
                </div>
            </div>
        </section>


    </div>
</template>

<script>
export default {
  name: "home",
  components: {}
};
</script>
