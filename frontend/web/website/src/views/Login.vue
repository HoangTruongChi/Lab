<template>
    <section class="section section-shaped section-lg my-0">
        <div class="shape shape-style-1 bg-gradient-default">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="container pt-lg-md">
            <div class="row justify-content-center">
                <div class="col-lg-5">
                    <card type="secondary" shadow
                          header-classes="bg-white pb-5"
                          body-classes="px-lg-5 py-lg-5"
                          class="border-0">
                        <template>
                          <div class="d-flex justify-content-center">

                              <img v-lazy="'img/theme/user1.png'" alt="Circle image"
                                   class="img-fluid rounded-circle shadow" style="width: 60px;">

                          </div>
                        </template>
                        <template>
                            <div class="text-center text-muted mb-4">
                                <H3>BKLab System</H3>
                            </div>
                            <form role="form" ref="loginForm"
                                  :model="loginForm"
                                  :rules="loginRules"
                                  class="login-form"
                                  autocomplete="on"
                                  label-position="left">
                                <base-input alternative
                                            class="mb-3"
                                            addon-left-icon="ni ni-email-83"
                                            ref="email"
                                            v-model="loginForm.username"
                                            placeholder="Email"
                                            name="email"
                                            type="text"
                                            tabindex="1"
                                            autocomplete="on">
                                </base-input>
                                <base-input alternative
                                            addon-left-icon="ni ni-lock-circle-open"
                                            ref="password"
                                            v-model="loginForm.password"
                                            placeholder="Password"
                                            name="password"
                                            type="password"
                                            tabindex="2"
                                            autocomplete="on"
                                            @keyup.enter.native="handleLogin">
                                </base-input>
                                <div class="text-center">
                                    <base-button type="primary" class="my-4"  :loading="loading"
                                                 @click.native.prevent="handleLogin">Sign In</base-button>
                                </div>
                            </form>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {
    name: "Login",
    data() {
        return {
            loginForm: {
                username: undefined,
                password: undefined
            },
            loginRules: {

            },
            loading: false,
        }
    },
    methods:{
        handleLogin(){
        //     this.$refs.loginForm.validate((valid) => {
        // if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then((data) => {
                this.$router.go("/registration")
                this.loading = false;
            })
            .catch((e) => {
                console.log(e)
                this.loading = false;
            });
        },
        passwordType(){}
    }

};
</script>
<style>
</style>
