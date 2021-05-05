<template>
  <div class="dashboard-container">
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {getRole} from '@/utils/auth'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    this.$router.push("/components/room")
  }
}
</script>
