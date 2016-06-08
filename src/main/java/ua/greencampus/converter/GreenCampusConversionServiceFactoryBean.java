package ua.greencampus.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Service;
import ua.greencampus.service.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikolay Yashchenko
 */
@Service("conversionService")
public class GreenCampusConversionServiceFactoryBean extends ConversionServiceFactoryBean {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private CourseService courseService;

    @Autowired
    @Lazy
    private FileService fileService;

    @Autowired
    @Lazy
    private ChatMessageService chatMessageService;

    @Autowired
    @Lazy
    private ChatDialogService chatDialogService;

    @Autowired
    @Lazy
    private QuizAnswerService quizAnswerService;

    @Autowired
    @Lazy
    private QuizQuestionService quizQuestionService;

    @Autowired
    @Lazy
    private QuizService quizService;

    @Autowired
    @Lazy
    private QuizResultService quizResultService;

    @Autowired
    @Lazy
    private CourseThemeService courseThemeService;

    @Autowired
    @Lazy
    private UserCourseService userCourseService;

    public GreenCampusConversionServiceFactoryBean() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new ChatMessageToDTOConverter());
        converters.add(new FileEntityToFileDTOConverter());
        converters.add(new ChatDialogToDTOConverter());
        converters.add(new QuizAnswerToQuizAnswerDTOConverter());
        converters.add(new CourseThemeToCourseThemeDTOConverter());
        setConverters(converters);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = getObject();
        ConverterRegistry registry = (ConverterRegistry) conversionService;
        registry.addConverter(new UserDTOtoUserConverter(userService, conversionService));
        registry.addConverter(new CourseDTOToCourseConverter(courseService, conversionService));
        registry.addConverter(new FileDTOtoFileEntityConverter(fileService));
        registry.addConverter(new ChatMessageDTOtoEntityConverter(chatMessageService, chatDialogService, userService));
        registry.addConverter(new QuizAnswerDTOToQuizAnswerConverter(quizAnswerService));
        registry.addConverter(new QuizQuestionDTOToQuizQuestionConverter(quizQuestionService, conversionService));
        registry.addConverter(new QuizDTOToQuizConverter(quizService, conversionService));
        registry.addConverter(new QuizResultDTOToQuizResultConverter(quizResultService, conversionService));
        registry.addConverter(new ChatDialogDTOToEntityConverter(userService));
        registry.addConverter(new UserToUserDTOConverter(conversionService));
        registry.addConverter(new QuizQuestionToQuizQuestionDTOConverter(conversionService));
        registry.addConverter(new QuizToQuizDTOConverter(conversionService));
        registry.addConverter(new QuizResultToQuizResultDTOConverter(conversionService));
        registry.addConverter(new CourseThemeDTOToCourseThemeConverter(courseThemeService));
        registry.addConverter(new CourseToCourseDTOConverter(userCourseService));
        registry.addConverter(new CourseToCourseWithThemesDTOConverter(userCourseService, conversionService));
    }
}