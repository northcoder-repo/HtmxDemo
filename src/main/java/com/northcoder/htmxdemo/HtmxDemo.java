package com.northcoder.htmxdemo;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.get;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinThymeleaf;
import java.util.HashMap;
import java.util.Map;
import org.thymeleaf.TemplateEngine;

public class HtmxDemo {

    public static void main(String[] args) {

        final TemplateEngine te = DemoTemplateEngine.configureEngine();

        Javalin.create(config -> {
            config.fileRenderer(new JavalinThymeleaf(te));
            config.router.apiBuilder(() -> {
                handleRoutes(te);
            });
        }).start(7070);
    }

    private static void handleRoutes(TemplateEngine te) {
        // full Thymeleaf page rendering:
        get("/", welcome);
        
        // Thymeleaf fragment rendering (uses HTMX Ajax):
        final ThymeleafFragmentHandler frag = new ThymeleafFragmentHandler(te);
        get("/message_one", frag::renderMessageOne);
        //get("/message_two", frag::renderMessageTwo);
        get("/message_two", msgTwo);
        get("/all_messages", frag::renderAllMessages);
    }
    
    private static final Handler welcome = ctx -> {
        Map<String, Object> model = new HashMap<>();
        model.put("mainTitle", "HTMX with Fragments");
        model.put("name", "John");
        ctx.render("demo", model);
    };

    private static final Handler msgTwo = ctx -> {
        Map<String, Object> model = new HashMap<>();
        model.put("msg2", 789);
        ctx.render("~{messages::msg_two}", model);
    };

}
