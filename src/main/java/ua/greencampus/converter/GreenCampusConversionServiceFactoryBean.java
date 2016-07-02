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
        converters.add(new ChatMessageToDtoConverter());
        converters.add(new FileEntityToFileDtoConverter());
        converters.add(new ChatDialogToDtoConverter());
        converters.add(new QuizAnswerToQuizAnswerDtoConverter());
        converters.add(new CourseThemeToCourseThemeDtoConverter());
        setConverters(converters);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = getObject();
        ConverterRegistry registry = (ConverterRegistry) conversionService;
        registry.addConverter(new UserDtoToUserConverter(userService, conversionService));
        registry.addConverter(new CourseDtoToCourseConverter(courseService, conversionService));
        registry.addConverter(new FileDtotoFileEntityConverter(fileService));
        registry.addConverter(new ChatMessageDtoToEntityConverter(chatMessageService, chatDialogService, userService));
        registry.addConverter(new QuizAnswerDtoToQuizAnswerConverter(quizAnswerService));
        registry.addConverter(new QuizQuestionDtoToQuizQuestionConverter(quizQuestionService, conversionService));
        registry.addConverter(new QuizDtoToQuizConverter(quizService, conversionService));
        registry.addConverter(new QuizResultDtoToQuizResultConverter(quizResultService, conversionService));
        registry.addConverter(new ChatDialogDtoToEntityConverter(userService));
        registry.addConverter(new UserToUserDtoConverter(conversionService));
        registry.addConverter(new QuizQuestionToQuizQuestionDtoConverter(conversionService));
        registry.addConverter(new QuizToQuizDtoConverter(conversionService));
        registry.addConverter(new QuizResultToQuizResultDtoConverter(conversionService));
        registry.addConverter(new CourseThemeDtoToCourseThemeConverter(courseThemeService));
        registry.addConverter(new CourseToCourseDtoConverter(userCourseService));
        registry.addConverter(new CourseToCourseWithThemesDtoConverter(userCourseService, conversionService));
    }
}