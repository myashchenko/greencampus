/**
 * Created by Arsenii on 06.04.2016.
 */

$('document').ready(function() {
    $('#btnSub').click(function() {
        $.ajax({
            url: '/api/user/pass/'+ $('#userid').val(),
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                'oldPassword' : $('#oldPass').val(),
                'newPassword' : $('#newPass').val()
            }),
            success: function(data){
                console.log(data);
                $('#regDone').html('Password updated.');
                $('#form-id').html('');
                setTimeout(function () {
                    window.location = '/user/account';
                }, 1000);
            },
            error: function(data){
                console.log(data);
                $('#regDone').html('Old password mismatch.');
            }
        })
    });

});

