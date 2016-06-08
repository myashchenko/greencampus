/**
 * Created by Arsenii on 06.04.2016.
 */
$('document').ready(function() {
    var avaResponse;
    $.ajax({
        url: '/api/user/' + $('#userid').val(),
        type: 'GET',
        success: function (data) {
            console.log(data);
            $('#inputEmail').val(data.entity.email);
            $('#inputName').val(data.entity.name);
            if (data.entity.avatar!= null && data.entity.avatar.path != null) {
                avaResponse = data.entity.avatar.path;
                $('#avatar').attr('src', avaResponse);
            }
        }
    });

    $('#btnSub').click(function() {
        $.ajax({
            url: '/api/user/'+ $('#userid').val(),
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                'email' : $('#inputEmail').val(),
                'name' : $('#inputName').val(),
                'avatar' : {'path' : avaResponse}
            }),
            success: function(data){
                console.log(data);
                window.location = '/user/account'
            },
            error: function(data){
                console.log(data);
            }

        })
    });

    $('#avatar-input').on('change', function() {
        var formData = new FormData();
        formData.append('file', $('input[type=file]')[0].files[0]);
        console.log("form data: " + formData);
        $.ajax({
            url : '/api/file/upload',
            data : formData,
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {
                console.log("data:" + data);
                avaResponse = data;
                $('#avatar').attr('src', avaResponse);
            },
            error : function(err) {
                console.log(err);
            }
        });
    })

});

