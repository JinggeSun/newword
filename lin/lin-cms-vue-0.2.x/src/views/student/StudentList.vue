<template>
  <el-table :data="tableData" stripe>
    <el-table-column prop="name" label="姓名" width="120"></el-table-column>
    <el-table-column prop="age" label="邮编" width="120"></el-table-column>
    <el-table-column prop="addr" label="地址"></el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'StudentList',
  data(){
    return {
      tableData:[{
         name:'郭靖',age:18,addr:'蒙古'
      }]
    }
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
