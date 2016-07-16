+function ($) { "use strict";

  $(function(){
 	
	// sparkline
	var sr, sparkline = function($re){
		$(".sparkline").each(function(){
			var $data = $(this).data();
			if($re && !$data.resize) return;
			($data.type == 'pie') && $data.sliceColors && ($data.sliceColors = eval($data.sliceColors));
			($data.type == 'bar') && $data.stackedBarColor && ($data.stackedBarColor = eval($data.stackedBarColor));
			$data.valueSpots = {'0:': $data.spotColor};
			$(this).sparkline('html', $data);
		});
	};
	$(window).resize(function(e) {
		clearTimeout(sr);
		sr = setTimeout(function(){sparkline(true)}, 500);
	});
	sparkline(false);

	// easypie
	var easypie = function(){
	$('.easypiechart').each(function(){
		var $this = $(this), 
		$data = $this.data(), 
		$step = $this.find('.step'), 
		$target_value = parseInt($($data.target).text()),
		$value = 0;
		$data.barColor || ( $data.barColor = function($percent) {
	        $percent /= 100;
	        return "rgb(" + Math.round(200 * $percent) + ", 200, " + Math.round(200 * (1 - $percent)) + ")";
	    });
		$data.onStep =  function(value){
			$value = value;
			$step.text(parseInt(value));
			$data.target && $($data.target).text(parseInt(value) + $target_value);
		}
		$data.onStop =  function(){
			$target_value = parseInt($($data.target).text());
			$data.update && setTimeout(function() {
		        $this.data('easyPieChart').update(100 - $value);
		    }, $data.update);
		}
			$(this).easyPieChart($data);
		});
	};
	easypie();
  
	// datepicker
	$(".datepicker-input").each(function(){ $(this).datepicker();});

	// dropfile
	$('.dropfile').each(function(){
		var $dropbox = $(this);
		if (typeof window.FileReader === 'undefined') {
		  $('small',this).html('File API & FileReader API not supported').addClass('text-danger');
		  return;
		}

		this.ondragover = function () {$dropbox.addClass('hover'); return false; };
		this.ondragend = function () {$dropbox.removeClass('hover'); return false; };
		this.ondrop = function (e) {
		  e.preventDefault();
		  $dropbox.removeClass('hover').html('');
		  var file = e.dataTransfer.files[0],
		      reader = new FileReader();
		  reader.onload = function (event) {
		  	$dropbox.append($('<img>').attr('src', event.target.result));
		  };
		  reader.readAsDataURL(file);
		  return false;
		};
	});

	// slider
	$('.slider').each(function(){
		$(this).slider();
	});

	// sortable
	if ($.fn.sortable) {
	  $('.sortable').sortable();
	}

	// slim-scroll
	$('.no-touch .slim-scroll').each(function(){
		var $self = $(this), $data = $self.data(), $slimResize;
		$self.slimScroll($data);
		$(window).resize(function(e) {
			clearTimeout($slimResize);
			$slimResize = setTimeout(function(){$self.slimScroll($data);}, 500);
		});
    $(document).on('updateNav', function(){
      $self.slimScroll($data);
    });
	});	

	// portlet
	$('.portlet').each(function(){
		$(".portlet").sortable({
	        connectWith: '.portlet',
            iframeFix: false,
            items: '.portlet-item',
            opacity: 0.8,
            helper: 'original',
            revert: true,
            forceHelperSize: true,
            placeholder: 'sortable-box-placeholder round-all',
            forcePlaceholderSize: true,
            tolerance: 'pointer'
	    });
    });

	// docs
  $('#docs pre code').each(function(){
	    var $this = $(this);
	    var t = $this.html();
	    $this.html(t.replace(/</g, '&lt;').replace(/>/g, '&gt;'));
	});

	// table select/deselect all
	$(document).on('change', 'table thead [type="checkbox"]', function(e){
		e && e.preventDefault();
		var $table = $(e.target).closest('table'), $checked = $(e.target).is(':checked');
		$('tbody [type="checkbox"]',$table).prop('checked', $checked);
	});

	// random progress
	$(document).on('click', '[data-toggle^="progress"]', function(e){
		e && e.preventDefault();

		var $el = $(e.target),
		$target = $($el.data('target'));
		$('.progress', $target).each(
			function(){
				var $max = 50, $data, $ps = $('.progress-bar',this).last();
				($(this).hasClass('progress-xs') || $(this).hasClass('progress-sm')) && ($max = 100);
				$data = Math.floor(Math.random()*$max)+'%';
				$ps.css('width', $data).attr('data-original-title', $data);
			}
		);
	});
	
	// add notes
	function addMsg($msg){
		var $el = $('.nav-user'), $n = $('.count:first', $el), $v = parseInt($n.text());
		$('.count', $el).fadeOut().fadeIn().text($v+1);
		$($msg).hide().prependTo($el.find('.list-group')).slideDown().css('display','block');
	}
	var $msg = '<a href="#" class="media list-group-item">'+
                  '<span class="pull-left thumb-sm text-center">'+
                    '<i class="fa fa-envelope-o fa-2x text-success"></i>'+
                  '</span>'+
                  '<span class="media-body block m-b-none">'+
                    'Sophi sent you a email<br>'+
                    '<small class="text-muted">1 minutes ago</small>'+
                  '</span>'+
                '</a>';	
  setTimeout(function(){addMsg($msg);}, 1500);

	//chosen
	$(".chosen-select").length && $(".chosen-select").chosen();
    //goup
    $.goup({
        trigger: 100,
        bottomOffset: 50,
        locationOffset: 20,
        titleAsText: true,
        containerColor:"#611b75",
        containerRadius:5
    });

  });
}(window.jQuery);
// 操作提示
var error_tip = function(msg,status){
  if(!$("#tip_box").length){
    $("body").append('<div class="modal fade" id="tip_box">'+
    '<div class="modal-dialog modal-sm">'+
    '<div class="alert alert-warning alert-block m-b-none" id="warning_tips">'+
    '<i class="fa fa-ok-sign"></i>'+
    '<strong>温馨提示：</strong>'+
    '<span id="error_tip"></span>'+
    '</div>'+
    '</div>'+
    '</div>');
  }
  $("#tip_box").modal('show');
  $("#error_tip").text(msg);
  if (status == 1) {  // success
    $('#warning_tips').removeClass("alert-warning").removeClass("alert-warning").addClass("alert-success").show();
  } else if(status == 2) { // warning
    $('#warning_tips').removeClass("alert-success").removeClass("alert-danger").addClass("alert-warning").show();
  }else{ // danger
    $('#warning_tips').removeClass("alert-success").removeClass("alert-warning").addClass("alert-danger").show();
  }
  setTimeout(function(){
    $("#tip_box").modal('hide');
  },1000);
}

var alert_box = function(msg){
  if(!$("#alert_box").length){
    $("body").append('<div class="modal fade" id="alert_box">'+
    '<div class="modal-dialog modal-sm">'+
    '<div class="modal-content">'+
    '<div class="modal-header">'+
    '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
    '<h4 class="modal-title text-center">'+msg+'</h4>'+
    '</div>'+
    '<div class="modal-body wrapper-lg text-center">'+
    '<button type="button" class="btn btn-s-sm btn-orange" id="alert_confirm" data-dismiss="modal">确定</button>'+
    '<button type="button" class="btn btn-s-sm btn-default m-l" id="alert_cancel" data-dismiss="modal">取消</button>'+
    '</div>'+
    '</div>'+
    '</div>'+
    '</div>');
  }
  $("#alert_box").modal('show');
}