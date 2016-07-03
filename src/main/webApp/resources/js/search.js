/**
 * Created by kolyan on 02.07.16.
 */
$('document').ready(function() {
    $.extend({
        getUrlVars: function () {
            var vars = [], hash;
            var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            for (var i = 0; i < hashes.length; i++) {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            return vars;
        },
        getUrlVar: function (name) {
            return $.getUrlVars()[name];
        }
    });

    $.fn.enterKey = function (fnc) {
        return this.each(function () {
            $(this).keypress(function (ev) {
                var keycode = (ev.keyCode ? ev.keyCode : ev.which);
                if (keycode == '13') {
                    fnc.call(this, ev);
                }
            })
        })
    };

    function chunk(arr, size) {
        var newArr = [];
        for (var i = 0; i < arr.length; i += size) {
            newArr.push(arr.slice(i, i + size));
        }
        return newArr;
    }

    var input = $('#search_text');
    var pathname = window.location.pathname;
    if (pathname.indexOf('/search') != -1 && $.getUrlVar('keywords') != null) {
        input.val($.getUrlVar('keywords'));
        doSearch(input.val());
    }

    input.enterKey(function () {
        var keywords = input.val();
        if (window.location.pathname.indexOf('/search') != -1) {
            window.history.pushState({}, null, '/search?keywords=' + keywords);
            doSearch(keywords);
        } else {
            window.location = '/search?keywords=' + keywords;
        }
    });

    function doSearch(keywords) {
        console.log(keywords);
        $.ajax({
            url: '/api/course/',
            data: {
                keywords : keywords
            },
            type: 'GET',
            success: function (data) {
                console.log(data);
                var all = $('#all-courses');
                all.empty();
                var courses = chunk(data.entities, 3);
                courses.forEach(function (item) {
                    all.append('<div class="row">');
                    item.forEach(function (course) {
                        all.append(
                            '<div class="col-md-4" > \
                                <article class="course-post"> \
                                    <figure> \
                                        <a href="/course/' + course.id + '" class="single_image"> \
                                                <div class="course-img-wrap"> \
                                                    <div class="overlay"> \
                                                        <i class="fa fa-search"></i> \
                                                    </div> \
                                                    <img src="https://lagunita.stanford.edu/c4x/Education/EDUC115N/asset/images_course_image.jpg" class="single_image"/> \
                                                </div> \
                                            </a> \
                                            <figcaption> \
                                                <h2><a href="/course/' + course.id + '" class="course-category" data-toggle="tooltip" data-placement="top" data-original-title="See more posts">' + course.title + '</a></h2> \
                                                <p><a href="/course/' + course.id + '" class="course-post-title">' + course.description + '<i class="fa fa-angle-right"></i></a></p> \
                                            </figcaption> \
                                        </figure> \
                                    </article> \
                                </div>'
                        )
                    });
                    all.append('</div>');
                });
            },
            error: function (data) {
                console.log(data);
            }
        })
    }
});