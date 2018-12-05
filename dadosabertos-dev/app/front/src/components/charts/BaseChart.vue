<script>
import { mapState } from 'vuex'

export default {
  computed: mapState({
    list: state => state.list
  }),
  watch: {
    list() {
      this.dataSource()
    }
  },
  data() {
    return {
      chart: null
    }
  }
}
</script>
