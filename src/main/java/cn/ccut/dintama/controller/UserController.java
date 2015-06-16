package cn.ccut.dintama.controller;

import cn.ccut.dintama.model.User;
import cn.ccut.dintama.model.UserException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private Map<String, User> users = new HashMap<>();

    public UserController() {
        users.put("dy", new User("dy", "123", "123"));
        users.put("wy", new User("wy", "123", "123"));
        users.put("zjf", new User("zjf", "123", "123"));
        users.put("ghy", new User("ghy", "123", "123"));
        users.put("nc", new User("nc", "123", "123"));
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated User user, BindingResult br, MultipartFile attach, HttpServletRequest request)throws Exception{
        if(br.hasErrors()){
            return "user/add";
        }
        System.out.println(attach.getBytes() + "    " + attach.getOriginalFilename());
        String path = request.getSession().getServletContext().getRealPath("/static/upload");
        System.out.println(path);
        File file = new File(path + "/" + attach.getOriginalFilename());
        FileUtils.copyInputStreamToFile(attach.getInputStream(), file);
        users.put(user.getUsername(), user);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String show(@PathVariable String username, Model model){
        model.addAttribute("user", users.get(username));

        return "user/show";
    }

    @RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
    public String update(@PathVariable String username, Model model){
        model.addAttribute(users.get(username));
        return "user/update";
    }

    @RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
    public String update(@PathVariable String username, @Validated User user, BindingResult br){
        if(br.hasErrors()){
            return "user/update";
        }
        users.remove(username);
        users.put(user.getUsername(), user);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable String username){
        users.remove(username);
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session){
        if(!users.containsKey(username)){
            throw new UserException("用户名不存在");
        }
        User user = users.get(username);
        if(!user.getPassword().equals(password)){
            throw new UserException("密码不正确");
        }
        session.setAttribute("loginUser", user);
        return "redirect:/user/users";
    }

    /**
     * 局部异常处理
     * */
    /*@ExceptionHandler(value = {UserException.class})
    public String error(UserException userException, HttpServletRequest request){
        request.setAttribute("e", userException);
        return "error";
    }*/

}
