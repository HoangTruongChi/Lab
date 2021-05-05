<template>
    <div class="profile-page">
        <section class="section-profile-cover section-shaped my-0">
            <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </section>
        <div class="container"> 
            <card shadow class="card-profile mt--300" no-body>
                <form role="form" class="text-center border border-light p-5" >

                    <p class="h1 mb-3">Thông tin thiết bị</p>

                    <p class="text-left h6 mb-3">Mã phòng: </p> 
                    <p class="text-left h6 mb-3">Tên phòng: </p>  
           
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="column" class="text-center">STT</th>
                                <th scope="column" class="text-center">Tên thiết bị</th>
                                <th scope="column" class="text-center">Số lượng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row" class="text-center">1</th>
                                <td>Máy hàn</td>
                                <td></td>
                            </tr>
                            <tr>
                                <th scope="row" class="text-center">2</th>
                                <td>Cưa</td>
                                <td></td>
                            </tr>
                            <tr>
                                <th scope="row" class="text-center">3</th>
                                <td>Bút điện</td>
                                <td></td>
                            </tr>
                            <tr>
                                <th scope="row" class="text-center">4</th>
                                <td>Dây cắm</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </card>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style>

</style>