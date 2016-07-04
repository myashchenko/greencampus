/**
 * Created by kolyan on 03.07.16.
 */
$('document').ready(function() {
    $(".connectButton").click(function(event){
        event.preventDefault();
        window.open("", "connectWindow", "width=800,height=500");
        var cTP = $(event.currentTarget).parent();
        cTP[0].setAttribute("target", "connectWindow");
        cTP[0].submit();
    });
});