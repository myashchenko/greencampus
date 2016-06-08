/**
 * Created by Arsenii on 06.04.2016.
 */
$('document').ready(function() {
    $.ajax({
        url: '/api/user/' + $('#userid').val(),
        type: 'GET',
        success: function (data) {
            console.log(data);
            $('#user').append('<div> Id:' + data.entity.id + ' email:' + data.entity.email + '</div>');
        }
    });
    $('#btnSub').click(function() {
        $.ajax({
            url: '/api/user/'+ $('#userid').val(),
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                'id' : $('#userid').val(),
                'email' : $('#inputEmail').val()
            }),
            success: function(data){
                console.log(data);
            },
            error: function(data){
                console.log(data);
            }
        })
    });

});

