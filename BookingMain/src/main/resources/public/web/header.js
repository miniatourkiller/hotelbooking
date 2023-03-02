var link = `<li><a href="posting.html">All Rooms</a></li>`
$(window).load(function(){
    if(document.cookie != null){
        $('.nav-links').append(link)
    }
})