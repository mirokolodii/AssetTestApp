$(document).ready(function() {
  // Navigation menu height
  var topMenuHeight = $("#navbar").outerHeight();
  // All section items
  var cardItems = $("div.card-header h2:first-child");
  // cardItems.css("background-color", "red");
  // alert(cardItems.length);
  // for (var i = 0; i < cardItems.length; i++) {
  //   // alert(i);
  //   $(cardItems[i]).css("background-color", "red");
  //   // alert(cardItems[i].html);
  // }
  
  
  cardItems.each(function(index) {
    var position = $(this).offset().top;
    console.log("Position of " + index + " card: " + position);
  });
  
  
  
  // On menu item click listener
  $(".nav-link").click(function(event) {
    event.preventDefault();
    
    /* Move to position */
    // Get section id to move to, which stored as attribute in menu item
    var sectionId = "#" + $(this).attr("data-section-id");
    var offsetValue = $(sectionId).offset().top - topMenuHeight - 10;
     $("html, body").animate({
       scrollTop: offsetValue 
     }, 500);    
    // Make only clicked menu item marked as active
    makeActiveMenuItem(this);
    
  });
  
  // Bind to scroll
  $(window).scroll(function() {
    // Get current window position after scroll
    var currentPosition = $(document).scrollTop() + topMenuHeight;
    // console.log("Current window position: " + currentPosition);
    // Filter card items to those, where position is behind current one
    var filteredItems = cardItems.filter(function() {
      return $(this).offset().top < currentPosition;
    });
    // console.log("Number of filtered items: " + filteredItems.length);
    
    
    
    if (filteredItems.length > 0) {
      // alert(filteredItems.length);
      var lastPosition = filteredItems.length - 1;
      var currentItem = filteredItems[lastPosition];
      var id = currentItem.id;
      var selector = "div a[data-section-id='" + id + "']";
      var menuItem = $(selector);
      makeActiveMenuItem(menuItem);
      // console.log(id);
      // console.log(selector);
      
    }
  });
});

var makeActiveMenuItem = function(item) {
  // Make only clicked menu item marked as active
    $(".nav-link").removeClass("active");
    $(item).addClass("active");
}