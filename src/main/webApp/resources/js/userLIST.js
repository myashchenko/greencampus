/**
 * Created by Arsenii on 30.04.2016.
 */



$('document').ready(function() {
    $.ajax({
        url: '/api/user/',
        type: 'GET',
        success: function (data) {
            console.log(data);
            data.entities.forEach(function(element, index, arr) {
                $('#all-users-ul').append('<li>' + element.name + '</li>')
            })
        },
        error: function (err) {
            console.log(err);
        }
    });
});
