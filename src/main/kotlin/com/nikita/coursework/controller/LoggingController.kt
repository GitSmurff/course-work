//package com.nikita.coursework.controller
//
//import lombok.extern.slf4j.Slf4j
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//import java.text.MessageFormat
//import java.util.*
//
//@Slf4j
//@RestController
//@RequestMapping("/v1/api/logs")
//class LoggingController {
//    var logger: Logger = LoggerFactory.getLogger(LoggingController::class.java)
//
//    @RequestMapping("/")
//    fun index(): String? {
//        val user = "john"
//        val application = "gateway"
//        logger.info("Bad experience for user $user" + " at time " + Calendar.getInstance().getTime())
//
//        // Substitution with one formatting anchor and one argument
//
//        // Substitution with one formatting anchor and one argument
//        logger.info("Bad experience for user {}", user)
//
//        // If you happen to forget to provide a substituting object
//
//        // If you happen to forget to provide a substituting object
//        logger.info("Bad experience for user {}")
//
//        // Substitution with two formatting anchors and two arguments
//
//        // Substitution with two formatting anchors and two arguments
//        logger.info("Bad experience for user {} at time {}", user, Calendar.getInstance().getTime())
//
//        // Substitution with three formatting anchors and three arguments
//
//        // Substitution with three formatting anchors and three arguments
//        logger.info(
//            "Bad experience for user {} at time {} while accessing {}",
//            user,
//            Calendar.getInstance().getTime(),
//            application
//        )
//
//        // Escaping formatting anchor
//
//        // Escaping formatting anchor
//        logger.info("ERROR CODE \\{}; Bad experience for user {} at time {}", user, Calendar.getInstance().getTime())
//
//        // Formatting anchor with data inside; no problem
//
//        // Formatting anchor with data inside; no problem
//        logger.info("ERROR CODE {22}; Bad experience for user {} at time {}", user, Calendar.getInstance().getTime())
//
//        // Crafting a message with Java's own MessageFormatter.
//        // Not a good idea as per SLF4J's documentation.
//        // 1. SLF4J's implementation is 10 times faster than that of MessageFormat.
//        // 2. Moreover to make sure that the evaluation happens only if that particular logging
//        // level is allowed, you need to do a check.
//
//        // Crafting a message with Java's own MessageFormatter.
//        // Not a good idea as per SLF4J's documentation.
//        // 1. SLF4J's implementation is 10 times faster than that of MessageFormat.
//        // 2. Moreover to make sure that the evaluation happens only if that particular logging
//        // level is allowed, you need to do a check.
//        if (logger.isInfoEnabled()) {
//            val message: String = MessageFormat.format(
//                "Bad experience for user {0} at time {1} while accessing {2}",
//                "",
//                Calendar.getInstance().getTime(),
//                application
//            )
//            logger.info(message)
//        }
//        return "Howdy! Check out the Logs to see the output..."
//    }
//}