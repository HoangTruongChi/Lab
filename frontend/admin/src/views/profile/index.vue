<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">

        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="Change Password" name="account">
                <account/>
              </el-tab-pane>
              <!-- <el-tab-pane v-if="user.role==='ADMIN'" label="Reset Password" name="timeline">
                <timeline />
              </el-tab-pane> -->
              
            </el-tabs>
          </el-card>
        </el-col>

      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Account from './components/Account'
import {getRole} from '@/utils/auth'

export default {
  name: 'Profile',
  components: { Account },
  data() {
    return {
      user: {},
      activeTab: 'account',
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.user = {
        name: this.name,
        role: getRole(),
      }
    },
  },
}
</script>
