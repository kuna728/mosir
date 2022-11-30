package pl.edu.wat.student.i9g1s1.mosir.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
    /* https://stackoverflow.com/questions/43913753/spring-boot-with-redirecting-with-single-page-angular2
     * Redirects all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
     */
    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        return "forward:/";
    }
}
