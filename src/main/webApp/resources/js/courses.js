/**
 * Created by kolyan on 04.04.16.
 */
$('document').ready(function() {
        $.ajax({
            url: '/api/course/',
            type: 'GET',
            success: function (data) {
                console.log(data);
                data.entities.forEach(function(element, index, arr) {
                    $('#all-courses').append('<div>ID: ' + element.id + '</div>')
                })
            }
        })
    }
);