import DashboardLayout from '../layout/DashboardLayout.vue'
// GeneralViews
import NotFound from '../pages/NotFoundPage.vue'

// Admin pages
import Overview from 'src/pages/Overview.vue'
import UserProfile from 'src/pages/UserProfile.vue'
import TableList from 'src/pages/TableList.vue'
import Typography from 'src/pages/Typography.vue'
import Icons from 'src/pages/Icons.vue'
import Maps from 'src/pages/Maps.vue'
import Notifications from 'src/pages/Notifications.vue'
import Upgrade from 'src/pages/Upgrade.vue'
import Hot10 from 'src/views/Hot10.vue'

import QnA from "@/views/QnA.vue";
import QnADetail from "@/views/QnADetail.vue";
import QnAInsert from "@/views/QnAInsert.vue";
import Board from "@/views/Board.vue";
import BoardDetail from "@/views/BoardDetail.vue";
import Hospital from "@/views/Hospital.vue"
import Apt from "@/views/apt.vue";
import AptDetail from "@/views/aptDetail.vue";
import KMap from "@/views/test.vue";
import Login from '@/views/Login.vue';
import FavList from '@/views/FavList.vue';
import SignUp from 'src/pages/UserProfile/SignUpForm.vue';
import Main from '@/components/kinesis/kinesis.vue';

const routes = [
  {
    path: '/',
    component: Main,
    redirect: '/admin/'
  },
  {
    path: '/admin',
    component: DashboardLayout,
    redirect: '/admin/main',
    children: [
      {
        path: 'main',
        name: 'main',
        component: Main,
      },
      {
        path: 'overview',
        name: 'Overview',
        component: Overview
      },
      {
        path: 'info',
        name: 'info',
        component: UserProfile
      },
      {
        path: 'table-list',
        name: 'Table List',
        component: TableList
      },
      {
        path: 'typography',
        name: 'Typography',
        component: Typography
      },
      {
        path: 'icons',
        name: 'Icons',
        component: Icons
      },
      {
        path: 'maps',
        name: 'Maps',
        component: Maps
      },
      {
        path: 'notifications',
        name: 'Notifications',
        component: Notifications
      },
      {
        path: 'upgrade',
        name: 'Upgrade to PRO',
        component: Upgrade
      },
      {
        path: "/hospital",
        name: "Hospital",
        component: Hospital,
      },
      {
        path: "/qna",
        name: "QnA",
        component: QnA,
      },
      {
        path: '/hot10',
        name: 'hot10',
        component: Hot10
      },
      {
        path: "/apt",
        name: "apt",
        component: Apt,
      },
      {
        path: "/aptDetail",
        name: "aptDetail",
        component: AptDetail,
      },
      {
        path: "/detail",
        name: "QuesDetail",
        component: QnADetail,
      },	
      {
        path: "/insert",
        name: "QuesInsert",
        component: QnAInsert,
      },
      {
        path: "/board",
        name: "Board",
        component: Board,
      },
      {
        path: "/board/detail/:idx",
        name: "BoardDetail",
        component: BoardDetail,
      },
      {
        path: "/test",
        name: "KMap",
        component: KMap,
      },
      {
        path: '/login',
        name: 'Login',
        component: Login,
      },
      {
        path: '/login/:nextRoute',
        name: 'LoginNextRoute',
        component: Login,
      },
      {
        path: '/fav',
        name: 'FavList',
        component: FavList,
      },
      {
        path: '/signup',
        name: 'SignUp',
        component: SignUp,
      },
    ]
  },
  { path: '*', component: NotFound }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
