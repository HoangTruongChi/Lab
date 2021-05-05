<template>
    <header class="header-global">
        <base-nav class="navbar-main" transparent type="" effect="light" expand>
            <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
                <img src="img/brand/BkLab.png" alt="logo">
            </router-link>

            <div class="row" slot="content-header" slot-scope="{closeMenu}">
                <div class="col-6 collapse-brand">
                    <a href="#">
                        <img src="img/brand/bkblue.png">
                    </a>
                </div>
                <div class="col-6 collapse-close">
                    <close-button @click="closeMenu"></close-button>
                </div>
            </div>

            <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
                <li class="nav-item" >
                    <a slot="title" href="#" class="nav-link"  role="button" style="color: white">
                        <i class="ni ni-ui-04 d-lg-none"></i>
                        <span class="nav-link-inner--text"><router-link to="/registration" STYLE="color: white">Đăng kí</router-link></span>
                    </a>
                </li>
                <li  class="nav-item">
                    <a slot="title" href="#" class="nav-link"  role="button">
                        <i class="ni ni-collection d-lg-none"></i>
                        <span class="nav-link-inner--text">Liên Hệ</span>
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav align-items-lg-center ml-lg-auto">
                <li class="nav-item d-none d-lg-block ml-lg-4">
                    <i class="ni ni-collection d-lg-none"></i>
                    <router-link style="color:white;" to="/login" class="nav-link-inner--text">Logout</router-link>
                </li>
            </ul>
        </base-nav>
    </header>
</template>
<script>
import BaseNav from "../components/BaseNav";
import BaseDropdown from "../components/BaseDropdown";
import CloseButton from "../components/CloseButton";

export default {
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown
  }
};
</script>
<style>
</style>
