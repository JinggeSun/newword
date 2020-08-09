const studentRouter = {
  route: null,
  name: null,
  title: '学生管理',
  type: 'folder',
  icon: 'iconfont icon-tushuguanli',
  filePath: 'views/student/',
  order: null,
  inNav: true,
  children: [
    {
      title: '添加学生',
      type: 'view',
      name: 'studentAdd',
      route: '/student/add',
      filePath: 'views/student/StudentAdd.vue',
      inNav: true,
      icon: 'iconfont icon-tushuguanli',
    },
    {
      title: '学生列表',
      type: 'view',
      name: 'studentList',
      route: '/student/list',
      filePath: 'views/student/StudentList.vue',
      inNav: true,
      icon: 'iconfont icon-tushuguanli',
    },
  ],
}

export default studentRouter
