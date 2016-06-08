/**
 * Created by kolyan on 14.05.16.
 */

var stompClient = Stomp.over(new SockJS('/ws/chat/dialogs'));

$('document').ready(function() {
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/dialog/unreadCount/' + window.userId, function (message) {
            $('#message-count').text(message.body);
        });

        stompClient.subscribe('/dialog/unreadCount/' + window.userId + '/new', function (message) {
            $('#message-count').text(parseInt($('#message-count').text()) + 1);
        });

        setTimeout(function () {
            stompClient.send("/ws/chat/dialog/unreadCount/" + window.userId, {}, JSON.stringify({}));
        }, 1000);
    });

});