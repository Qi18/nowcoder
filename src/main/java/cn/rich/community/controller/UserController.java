package cn.rich.community.controller;

import cn.rich.community.entity.User;
import cn.rich.community.service.UserService;
import cn.rich.community.util.CommunityConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController implements CommunityConstant {

    @Autowired
    private UserService userService;

//    @Autowired
//    private LikeService likeService;
//
//    @Autowired
//    private FollowService followService;

//    }

//     获取user信息
    @RequestMapping(path = "/profile/getUserInfo", method = RequestMethod.GET)
    public User getUserInfo(@RequestParam("userId") int userId) {
        User user = userService.findUserById(userId);
        Optional.ofNullable(user).orElseThrow(() -> new RuntimeException("该用户不存在!"));

//        // 点赞数量
//        int likeCount = likeService.findUserLikeCount(userId);
//        model.addAttribute("likeCount", likeCount);
//
//        // 关注数量
//        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
//        model.addAttribute("followeeCount", followeeCount);
//        // 粉丝数量
//        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
//        model.addAttribute("followerCount", followerCount);
//        // 是否已关注
//        boolean hasFollowed = false;
//        if (hostHolder.getUser() != null) {
//            hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
//        }

        return user;
    }

}
