import Home from '@/views/oj/Home.vue'
import SetNewPassword from "@/views/oj/user/SetNewPassword.vue"
import UserHome from "@/views/oj/user/UserHome.vue"
import Setting from "@/views/oj/user/Setting.vue"
import ProblemLIst from "@/views/oj/problem/ProblemList.vue"
import Logout from "@/views/oj/user/Logout.vue"
import SubmissionList from "@/views/oj/status/SubmissionList.vue"
import SubmissionDetails from "@/views/oj/status/SubmissionDetails.vue"
import ContestList from "@/views/oj/contest/ContestList.vue"
import Problem from "@/views/oj/problem/Problem.vue"
import ACMRank from "@/views/oj/rank/ACMRank.vue"
import OIRank from "@/views/oj/rank/OIRank.vue"
import ContestDetails from "@/views/oj/contest/ContestDetails.vue"
import ContestProblemList from "@/views/oj/contest/children/ContestProblemList.vue"
import ContestRank from "@/views/oj/contest/children/ContestRank.vue"
import ACMInfoAdmin from "@/views/oj/contest/children/ACMInfoAdmin.vue"
import Announcements from "@/components/oj/common/Announcements.vue"
import Introduction from "@/views/oj/about/Introduction.vue"
import NotFound from "@/views/404.vue"
const ojRoutes = [
  {
    path: '/',
    redirect: '/home',
    component: Home,
    meta: { title: 'Home' }
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: { title: 'Home' }
  },
  {
    path: '/problem',
    name: 'ProblemList',
    component: ProblemLIst,
    meta: { title: 'Problem' }
  },
  {
    path: '/problem/:problemID',
    name: 'problemDetails',
    component: Problem,
    meta: { title: 'Problem Details' }
  },
  {
    path: '/contest',
    name: 'ContestList',
    component: ContestList,
    meta: { title: 'Contest' }
  },
  {
    path: '/status',
    name: 'SubmissionList',
    component: SubmissionList,
    meta: { title: 'Status' }
  },
  {
    path: '/submission-detail/:submitID',
    name: 'SubmissionDeatil',
    component: SubmissionDetails,
    meta: { title: 'Submission Deatil' }
  },
  {
    path: '/acm-rank',
    name: 'ACM Rank',
    component: ACMRank,
    meta: { title: 'ACM Rank' }
  },
  {
    path: '/oi-rank',
    name: 'OI Rank',
    component: OIRank,
    meta: { title: 'OI Rank' }
  },
  {
    path: '/reset-password',
    name: 'SetNewPassword',
    component: SetNewPassword,
    meta: { title: 'Reset Password' }
  },
  {
    name: 'UserHome',
    path: '/user-home',
    component: UserHome,
    meta: { requireAuth: true, title: 'User Home' }
  },
  {
    name: 'Setting',
    path: '/setting',
    component: Setting,
    meta: { requireAuth: true, title: 'Setting' }
  },
  {
    name: 'Logout',
    path: '/logout',
    component: Logout,
    meta: { requireAuth: true, title: 'Logout' }
  },
  {
    name: 'contestDetails',
    path: '/contest/:contestID/',
    component:ContestDetails,
    meta: {title: 'Contest Details'},
    children: [
      {
        name: 'contestSubmissionList',
        path: 'submissions',
        component: SubmissionList,
        meta: { title: 'Contest Submission' }
      },
      {
        name: 'contestProblemList',
        path: 'problems',
        component: ContestProblemList,
        meta: { title: 'Contest Problem' }
      },
      {
        name: 'contestProblemDetails',
        path: 'problem/:problemID/',
        component: Problem,
        meta: { title: 'Contest Problem Details' }
      },
      {
        name: 'contestAnnouncementList',
        path: 'announcements',
        component: Announcements,
        meta: { title: 'Contest Announcement' }
      },
      {
        name: 'contestRank',
        path: 'rank',
        component: ContestRank,
        meta: { title: 'Contest Rank' }
      },
      {
        name: 'contestACInfo',
        path: 'ac-info',
        component: ACMInfoAdmin,
        meta: { title: 'Contest AC Info' }
      }
    ]
  },
  {
    path: '/introduction',
    meta: {title: 'Introduction'},
    component:Introduction,
    meta: { title: 'Introduction' }
  },
  {
    path: '*',
    meta: {title: '404'},
    component:NotFound,
    meta: { title: '404' }
  }
]
export default ojRoutes
