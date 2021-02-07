package top.hcode.hoj;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.protocol.PacketReceivedTimeHolder;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import top.hcode.hoj.common.result.CommonResult;
import top.hcode.hoj.dao.*;
import top.hcode.hoj.pojo.entity.Contest;
import top.hcode.hoj.pojo.entity.Role;
import top.hcode.hoj.pojo.entity.Session;
import top.hcode.hoj.pojo.entity.UserInfo;
import top.hcode.hoj.pojo.vo.AnnouncementVo;
import top.hcode.hoj.pojo.vo.ContestVo;
import top.hcode.hoj.pojo.vo.RoleAuthsVo;
import top.hcode.hoj.pojo.vo.UserRolesVo;
import top.hcode.hoj.service.UserInfoService;
import top.hcode.hoj.service.UserRoleService;
import top.hcode.hoj.service.impl.AnnouncementServiceImpl;
import top.hcode.hoj.service.impl.UserInfoServiceImpl;
import top.hcode.hoj.service.impl.UserRoleServiceImpl;
import top.hcode.hoj.utils.Constants;
import top.hcode.hoj.utils.IpUtils;
import top.hcode.hoj.utils.JsoupUtils;
import top.hcode.hoj.utils.RedisUtils;

import java.io.IOException;
import java.net.*;
import java.text.MessageFormat;
import java.util.*;

/**
 * @Author: Himit_ZH
 * @Date: 2020/10/24 17:24
 * @Description:
 */
@SpringBootTest
public class DataBackupApplicationTests {

    @Autowired
    private JudgeMapper judgeMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private UserInfoServiceImpl userInfoService;


    @Autowired
    private AnnouncementServiceImpl announcementService;

    @Test
    public void Test1() {
//        UserRolesVo roles = userRoleMapper.getUserRoles("c5ddbe4b38d641bea7d87ae0e102260d",null);
//        System.out.println(roles);
//        IPage<UserRolesVo> admin = userRoleService.getUserList(10, 1, "admin");
//        System.out.println(admin.getPages());
//        System.out.println(admin.getSize());
//        System.out.println(admin.getRecords());
//        List<String> list = new LinkedList<>();
//        list.add("1");
//        list.add("2");
//        boolean b = userInfoService.removeByIds(list);
//        System.out.println(b);
//        UserInfo userInfo = new UserInfo().
//                setUsername("111")
//                .setPassword("1111")
//                .setEmail("11111");
//        boolean b = userInfoService.saveOrUpdate(userInfo);
//        if (b){
//            System.out.println(userInfo);
//        }

//        List<AnnouncementVo> contestAnnouncement = announcementService.getContestAnnouncement(1L);
//        System.out.println(contestAnnouncement.size());
        String test = "{1}....{2}";
        String command = "/usr/bin/java -cp {1} -XX:MaxRAM={2}k -Djava.security.manager -Dfile.encoding=UTF-8 -Djava.security.policy==/etc/java_policy -Djava.awt.headless=true Main";
        String exePath = "/judge/run/32/1.exe";
        String exeDir = "/judge/run/32";
        int maxMemory = 1024*10;
        List<String> commandList = Arrays.asList(MessageFormat.format(command, exePath, exeDir, (maxMemory / 1024)).split(" "));
        System.out.println(commandList);
    }

    @Autowired
    private NacosDiscoveryProperties discoveryProperties;
    @Test
    public void Test2() {
        String clusterName = discoveryProperties.getClusterName();
        System.out.println(clusterName);
        // 获取该微服务的所有健康实例
        // 获取服务发现的相关API
        NamingService namingService = discoveryProperties.namingServiceInstance();
        try {
            // 获取该微服务的所有健康实例
            List<Instance> instances = namingService.selectInstances("hoj-judge-server", true);
            System.out.println(instances);
        } catch (NacosException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Test3() {
        String serviceIp = IpUtils.getServiceIp();
        System.out.println(serviceIp);
    }


    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void Test4() {
////        int todayJudgeNum = judgeMapper.getTodayJudgeNum();
//        List<ContestVo> withinNext14DaysContests = contestMapper.getWithinNext14DaysContests();
//        System.out.println(withinNext14DaysContests);
        String result = restTemplate.getForObject("http://129.204.177.72:8848/nacos/v1/ns/instance?ip=192.168.226.1&port=8010&serviceName=hoj-judge-server&metadata=%7B%22maxTaskNum%22%3A8%2C%22currentTaskNum%22%3A1%7D", String.class);
        System.out.println(result);
    }

    @Test
    public void Test5() throws IOException {
        Enumeration<NetworkInterface> ifaces = null;
        try {
            ifaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {

        }
        String siteLocalAddress = null;
        while (ifaces.hasMoreElements()) {
            NetworkInterface iface = ifaces.nextElement();
            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                String hostAddress = addr.getHostAddress();
                if (addr instanceof Inet4Address) {
                    if (addr.isSiteLocalAddress()) {
                        siteLocalAddress = hostAddress;
                    } else {
                        break;
                    }
                }
            }
        }
       System.out.println(siteLocalAddress == null ? "" : siteLocalAddress);
    }

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void Test6() {
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.isNotNull("cf_username");
        List<UserInfo> userInfoList = userInfoMapper.selectList(userInfoQueryWrapper);
        for (UserInfo userInfo : userInfoList) {
            System.out.println(userInfo.getCfUsername());
        }
    }


}