//package cn.rich.community.controller;
//
//import cn.rich.community.service.DiscussPostService;
//import cn.rich.community.entity.Comment;
//import cn.rich.community.entity.DiscussPost;
//import cn.rich.community.entity.Event;
//import cn.rich.community.event.EventProducer;
//import cn.rich.community.service.CommentService;
//import cn.rich.community.util.CommunityConstant;
//import cn.rich.community.util.HostHolder;
//import cn.rich.community.util.RedisKeyUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.Date;
//
//@Controller
//@RequestMapping("/comment")
//public class CommentController implements CommunityConstant {
//
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private HostHolder hostHolder;
//
//    @Autowired
//    private EventProducer eventProducer;
//
//    @Autowired
//    private DiscussPostService discussPostService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
//    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
//        comment.setUserId(hostHolder.getUser().getId());
//        comment.setStatus(0);
//        comment.setCreateTime(new Date());
//        commentService.addComment(comment);
//
//        // 触发评论事件
//        Event event = new Event()
//                .setTopic(TOPIC_COMMENT)
//                .setUserId(hostHolder.getUser().getId())
//                .setEntityType(comment.getEntityType())
//                .setEntityId(comment.getEntityId())
//                .setData("postId", discussPostId);
//        if (comment.getEntityType() == ENTITY_TYPE_POST) {//帖子的评论
//            DiscussPost target = discussPostService.findDiscussPostById(comment.getEntityId());
//            event.setEntityUserId(target.getUserId());
//        } else if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {//评论的评论
//            Comment target = commentService.findCommentById(comment.getEntityId());
//            event.setEntityUserId(target.getUserId());
//        }
//        eventProducer.fireEvent(event);
//
//        if (comment.getEntityType() == ENTITY_TYPE_POST) {
//            // 触发发帖事件
//            event = new Event()
//                    .setTopic(TOPIC_PUBLISH)
//                    .setUserId(comment.getUserId())
//                    .setEntityType(ENTITY_TYPE_POST)
//                    .setEntityId(discussPostId);
//            eventProducer.fireEvent(event);
//            // 计算帖子分数
//            String redisKey = RedisKeyUtil.getPostScoreKey();
//            redisTemplate.opsForSet().add(redisKey, discussPostId);
//        }
//
//        return "redirect:/discuss/detail/" + discussPostId;
//    }
//
//}
