/**
 * Created by kolyan on 27.04.16.
 */
var stompClient = Stomp.over(new SockJS('/ws/chat/dialogs'));

$('document').ready(function() {
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/dialog/' + window.userId + '/new', function (message) {
            var entry = JSON.parse(message.body);
            var userPanel = $('#user_panel_id');
            var unreadCount = 0;
            if (entry.unreadCount != null) {
                unreadCount = typeof entry.unreadCount[window.userId] === 'undefined' ? 0 : entry.unreadCount[window.userId];
            }
            var avatarPath = 'http://www.localevolutionmedia.com/wp-content/uploads/2015/02/ncf_userpic1.png';
            if (entry.avatarPath != null) {
                avatarPath = entry.avatarPath;
            }
            var newOption =
                '<li class="media">' +
                    '<div id="media-body-' + entry.id + '" class="media-body">' +
                        '<div class="media">' +
                            '<a class="pull-left" href="#">' +
                                '<img class="media-object img-circle" style="max-height:40px;max-width:40px; margin-top: 5px; margin-left: 5px" src="' + avatarPath + '" />' +
                            '</a>' +
                            '<div class="media-body" >' +
                                '<h5 id="dialog-id" data-dialog-id="' + entry.id + '" onclick="showDialog(event)" style="cursor: pointer">'+ entry.dialogName +'</h5>' +
                                '<small class="text-muted"><span id="message-count">' + unreadCount + '</span> unread messages</small>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</li>';

            userPanel.prepend(newOption);

            stompClient.subscribe('/dialog/' + entry.id + '/' + window.userId, function (messages) {
                console.log('SHOW DIALOG WITH ID');
                $('#chat-messages-panel').html('');
                var messagesArr = JSON.parse(messages.body);
                messagesArr.forEach(function(entry) {
                    showMessage(entry);
                });

                $("#scroll-chat-panel").animate({ scrollTop: $('#scroll-chat-panel')[0].scrollHeight}, 1000);
            });

            stompClient.subscribe('/dialog/' + entry.id + '/new/' + window.userId, function (message) {
                console.log('SHOW NEW MESSAGE');
                var messageBody = JSON.parse(message.body);
                if ($('#current-dialog-id').val() == entry.id) {
                    showMessage(messageBody);
                    // send to server that messages seen already
                    stompClient.send('/ws/chat/dialog/' + entry.id + '/read/' + window.userId, {}, JSON.stringify({}));
                    $("#scroll-chat-panel").animate({ scrollTop: $('#scroll-chat-panel')[0].scrollHeight}, 1000);
                } else {
                    var messageCount = $('#media-body-' + entry.id).find('#message-count');
                    messageCount.text(parseInt(messageCount.text()) + 1);
                }
            });
        });

        stompClient.subscribe('/dialogs/' + window.userId, function (message) {
            showDialogs(message.body);
        });

        // wait for connection
        setTimeout(function() {
            stompClient.send("/ws/chat/dialogs/" + window.userId, {}, JSON.stringify({}));
        }, 1000);
    });

    $('#send-message-button').click(function() {
        var messageText = $('#new-message-text').val();
        console.log(messageText);
        var dialogId = $('#current-dialog-id').val();
        if (dialogId.length != 0) {
            stompClient.send("/ws/chat/dialog/" + dialogId + '/new/' + window.userId, {}, JSON.stringify({
                dialogId: dialogId,
                text: messageText
            }));
            $('#new-message-text').val('');
        }
    });

    function showDialogs(dialogResponse) {
        console.log('SHOW DIALOGS');
        var dialogResponseObject = JSON.parse(dialogResponse);

        dialogResponseObject.forEach(function (entry) {
            var userPanel = $('#user_panel_id');
            var unreadCount = typeof entry.unreadCount[window.userId] === 'undefined' ? 0 : entry.unreadCount[window.userId];
            var avatarPath = 'http://www.localevolutionmedia.com/wp-content/uploads/2015/02/ncf_userpic1.png';
            if (entry.avatarPath != null) {
                avatarPath = entry.avatarPath;
            }
            var newOption =
                '<li class="media">' +
                    '<div id="media-body-' + entry.id + '" class="media-body">' +
                        '<div class="media">' +
                            '<a class="pull-left" href="#">' +
                                '<img class="media-object img-circle" style="max-height:40px;max-width:40px; margin-top: 5px; margin-left: 5px" src="' + avatarPath + '" />' +
                            '</a>' +
                            '<div class="media-body" >' +
                                '<h5 id="dialog-id" data-dialog-id="' + entry.id + '" onclick="showDialog(event)" style="cursor: pointer">'+ entry.dialogName +'</h5>' +
                                '<small class="text-muted"><span id="message-count">' + unreadCount + '</span> unread messages</small>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</li>';

            userPanel.append(newOption);

            stompClient.subscribe('/dialog/' + entry.id + '/' + window.userId, function (messages) {
                console.log('SHOW DIALOG WITH ID');
                $('#chat-messages-panel').html('');
                var messagesArr = JSON.parse(messages.body);
                messagesArr.forEach(function(entry) {
                    showMessage(entry);
                });

                $("#scroll-chat-panel").animate({ scrollTop: $('#scroll-chat-panel')[0].scrollHeight}, 1000);
            });

            stompClient.subscribe('/dialog/' + entry.id + '/new/' + window.userId, function (message) {
                console.log('SHOW NEW MESSAGE');
                var messageBody = JSON.parse(message.body);
                if ($('#current-dialog-id').val() == entry.id) {
                    showMessage(messageBody);
                    // send to server that messages seen already
                    stompClient.send('/ws/chat/dialog/' + entry.id + '/read/' + window.userId, {}, JSON.stringify({}));
                    $("#scroll-chat-panel").animate({ scrollTop: $('#scroll-chat-panel')[0].scrollHeight}, 1000);
                } else {
                    var messageCount = $('#media-body-' + entry.id).find('#message-count');
                    messageCount.text(parseInt(messageCount.text()) + 1);
                }
            });

            var hash = window.location.hash.replace("#", "");
            if (hash.length != 0) {
                showDialogById(hash);
            }
        });
    }

    function showMessage(messageObject) {
        console.log('SHOW MESSAGE');
        var chatMessagePanel = $('#chat-messages-panel');
        var avatarPath = 'http://www.localevolutionmedia.com/wp-content/uploads/2015/02/ncf_userpic1.png';
        if (messageObject.avatarPath != null) {
            avatarPath = messageObject.avatarPath;
        }
        var newOption =
            '<li class="media">' +
                '<div class="media-body">' +
                    '<div class="media">' +
                        '<a class="pull-left" href="#">' +
                            '<img class="media-object img-circle" style="max-height:40px; max-width: 40px" src="' + avatarPath + '"/>' +
                        '</a>' +
                        '<div class="media-body">' + messageObject.text + '<br/>' +
                            '<small class="text-muted">' + messageObject.userFromName + ' | ' + messageObject.date + '</small>' +
                            '<hr/>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</li>';

        chatMessagePanel.append(newOption);
    }
});

function showDialog(event) {
    var dialogId = event.target.getAttribute('data-dialog-id');
    showDialogById(dialogId);
}

function showDialogById(dialogId) {
    $('#message-history').css({"visibility":"visible"});

    var currentDialogId = $('#current-dialog-id').val();
    $('#media-body-' + currentDialogId).css({"background-color": "white"});

    $('#media-body-' + dialogId).css({"background-color": "#00BFFF"});
    var messageCount = $('#media-body-' + dialogId).find('#message-count');
    messageCount.text(0);

    $('#current-dialog-id').val(dialogId);

    // send to server request about messages by dialog
    stompClient.send("/ws/chat/dialog/" + dialogId + '/' + window.userId, {}, JSON.stringify({}));

    // send to server that messages seen already
    stompClient.send('/ws/chat/dialog/' + dialogId + '/read/' + window.userId, {}, JSON.stringify({}));
}

$(window).on('hashchange', function() {
    var hash = window.location.hash.replace("#", "");
    showDialogById(parseInt(hash));
});