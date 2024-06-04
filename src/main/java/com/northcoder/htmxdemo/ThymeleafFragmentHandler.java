package com.northcoder.htmxdemo;

import io.javalin.http.Context;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.thymeleaf.TemplateEngine;

public class ThymeleafFragmentHandler {

    protected ThymeleafFragmentHandler(TemplateEngine te) {
        this.te = te;
    }

    private final TemplateEngine te;
    private final Locale locale = Locale.getDefault();

    protected void renderMessageOne(Context ctx) {
        String template = "messages";
        Set fragments = Set.of("msg_one");
        Map model = Map.ofEntries(
                Map.entry("msg1", "Fred"),
                Map.entry("foo", "Jane")
        );
        renderFragment(ctx, template, fragments, model);
    }
    
    protected void renderMessageTwo(Context ctx) {
        String template = "messages";
        Set fragments = Set.of("msg_two");
        Map model = Map.ofEntries(
                Map.entry("msg2", 456)
        );
        renderFragment(ctx, template, fragments, model);
    }
    
    protected void renderAllMessages(Context ctx) {
        String template = "messages";
        Set fragments = Set.of("msg_one", "msg_two");
        Map model = Map.ofEntries(
                Map.entry("msg1", "Fred"),
                Map.entry("foo", "Jane"),
                Map.entry("msg2", 456)
        );
        renderFragment(ctx, template, fragments, model);
    }
    
    private void renderFragment(Context ctx, String template, Set fragments, Map model) {
        ctx.html(te.process(
                template,
                fragments,
                new org.thymeleaf.context.Context(
                        locale,
                        model
                )
        ));
    }

}
