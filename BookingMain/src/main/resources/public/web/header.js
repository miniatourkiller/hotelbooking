var linkage = `<li><a href="posting.html">Hosting rooms</a></li>
<li><a id="logout">logout</a></li>`
window.addEventListener('load', function(){
	 if(document.cookie.length>0 && this.document.cookie!="myid"){
        $('.nav-links').append(linkage)
    }
    $('#logout').click((e)=>{
	e.preventDefault()
	$.ajax({
		url:"http://localhost:5070/logout",
		success: function(data){
			if(data == "done"){
				document.cookie = "myid",+" ",-1;
                if(document.cookie == "myid"){
                    window.location.reload()
                }
			}
		}
	})
})
})