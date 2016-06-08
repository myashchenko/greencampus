/**
 * Created by Arsenii on 04.04.2016.
 */
$('document').ready(function () {
    $.ajax({
        url: '/api/user/' + $('#userid').val(),
        type: 'GET',
        success: function (data) {
            console.log(data);
            $('#Name').html(data.entity.name);
            $('#Mail').html(data.entity.email);
            if (data.entity.avatar != null) {
                $('#avatar').attr('src', data.entity.avatar.path);
            }

        }
    })
});