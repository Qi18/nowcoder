package cn.rich.community.service;

import cn.rich.community.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rich
 * @since 2024-07-11
 */
public interface UserService extends IService<User> {

    User findUserById(int userId);

    void register(User user);

}
