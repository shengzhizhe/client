/**
* Opton.js
* Description: 
*
* 
* 
* 
*/
(function() {
    'use strict'
    var tpj=jQuery;         
    var revapi113;
    tpj(document).ready(function() {
        tpj('#rev_slider_113_1').find('li').each(function() {
            var li = jQuery(this),
                o = new Object();
            o = jQuery.extend(o,li.data());
            li.data('saved',o);
        });

        if(tpj("#rev_slider_113_1").revolution == undefined){
            revslider_showDoubleJqueryError("#rev_slider_113_1");
        }else{
            revapi113 = tpj("#rev_slider_113_1").show().revolution({
                sliderType:"standard",
                jsFileLocation:"../../revolution/js/",
                sliderLayout:"auto",
                dottedOverlay:"none",
                delay:9000,
                navigation: {
                    keyboardNavigation:"off",
                    keyboard_direction: "horizontal",
                    mouseScrollNavigation:"off",
                    onHoverStop:"off",
                    arrows: {
                        style:"uranus",
                        enable:true,
                        hide_onmobile:true,
                        hide_under:778,
                        hide_onleave:true,
                        hide_delay:200,
                        hide_delay_mobile:1200,
                        tmp:'',
                        left: {
                            h_align:"left",
                            v_align:"center",
                            h_offset:20,
                            v_offset:0
                        },
                        right: {
                            h_align:"right",
                            v_align:"center",
                            h_offset:20,
                            v_offset:0
                        }
                    }
                },
                gridwidth:1230,
                gridheight:892,
                lazyType:"single",
                shadow:0,
                spinner:"off",
                stopLoop:"on",
                stopAfterLoops:0,
                stopAtSlide:1,
                shuffle:"off",
                autoHeight:"off",
                disableProgressBar:"on",
                hideThumbsOnMobile:"on",
                hideSliderAtLimit:0,
                hideCaptionAtLimit:0,
                hideAllCaptionAtLilmit:0,
                debugMode:false,
                fallbacks: {
                    simplifyAll:"off",
                    nextSlideOnWindowFocus:"off",
                    disableFocusListener:false,
                }
            });
        }
    });
    $('body').on('click', '#menu-btn', function(event) {
        $(this).toggleClass('open');
        $('.navbar > ul').toggleClass('open');
    });
    $('body').on('click', '#hamburg-menu', function(event) {        
        $('.side-menu').toggleClass('open');
    });
    $('body').on('click', '#close', function(event) {        
        $('.side-menu').toggleClass('open');
    });
    $('.navbar ul > li').on('click', function(e) {
        if(e.target.tagName == "A")
        {
            if(e.target.attributes[0].nodeValue == "#")
            {
                e.preventDefault();
                if($(this).hasClass('open'))
                {
                    $(this).removeClass('open');    
                }
                else
                {
                    $(".navbar ul > li").removeClass("open");  
                    $(this).addClass('open');       
                }
            }
        }
        if(e.target.tagName != "A"){
            e.preventDefault(); 
            if($(this).hasClass('open'))
            {
                $(this).removeClass('open');    
            }
            else
            {
                $(".navbar ul > li").removeClass("open");  
                $(this).addClass('open');       
            }
        }
    });
    $('#portfolio-section').each(function() {
        $(this).children('.item').matchHeight({
            byRow: true
        });
    });
    $('#news-section').each(function() {
        $(this).children('.news-item').matchHeight({
            byRow: true
        });
    });
    $('#pricing-table .clearfix').each(function() {
        $(this).children('.table-box').matchHeight({
            byRow: true
        });
    });
    $('#scrollup').on('click', function () {
        $("html, body").animate({
            scrollTop: 0
        }, 600);
        return false;
    });  
    $('#page-scroll').on('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 2000, 'easeInOutExpo');
        event.preventDefault();
    });	
    $('#testimonial-slider').slick({
        infinite: true,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        dots: true,        
    });
    $('#mils-slider').slick({
        infinite: false,
        speed: 300,
        slidesToShow: 5,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        arrows: false,
        dots: false,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 5,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });
    $('#project-img').slick({
        infinite: true,
        speed: 300,
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        dots: false,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    infinite: true,
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });
    $('#blog-img-slider').slick({
        infinite: true,
        speed: 300,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
        dots: false,
    });    
    $('body').on('click', '#list', function () {
        $('.product-category-wrap').addClass('list-view');
        $('.product-category-wrap').removeClass('grid-view');
        $(this).addClass('active');
        $('#grid').removeClass('active');
    });
    $('body').on('click', '#grid', function () {
        $('.product-category-wrap').removeClass('list-view');
        $('.product-category-wrap').addClass('grid-view');
        $(this).addClass('active');
        $('#list').removeClass('active');
    }); 
    var portfolioIsotope = function() {         
        if ( $().isotope ) {          
            var $container = $('.build-portfolio');
            $container.imagesLoaded(function(){
                $container.isotope({
                    itemSelector: '.items',
                    transitionDuration: '1s',
                    percentPosition: true,               
                    masonry: {
                        columnWidth: '.grid-sizer'
                    }
                });
            });

            $('.portfolio-filter li').on('click',function() {                           
                var selector = $(this).find("a").attr('data-filter');
                $('.portfolio-filter li').removeClass('active');
                $(this).addClass('active');
                $container.isotope({ filter: selector });
                return false;
            });            
        };
    };
    var scrollBar = function() {
        jQuery(window).on("load",function(){
            jQuery(".select-brand").mCustomScrollbar();
            jQuery(".side-menu .nav-wrapper > li").mCustomScrollbar();
            jQuery(".sub-menu").mCustomScrollbar();            
        }); 
    };
    var rangeSlider = function() {         
        $( "#slider-range" ).slider({
            range: true,
            min: 0,
            max: 500,
            values: [ 60, 196 ],
            slide: function( event, ui ) {
                $( "#amount1" ).val( "$" + ui.values[ 0 ] );
                $( "#amount2" ).val( "$" + ui.values[ 1 ] );
            }
        });
        $( "#amount1" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) );
        $( "#amount2" ).val( "$" + $( "#slider-range" ).slider( "values", 1 ) );
    };
    var prdSlider = function() { 
        $("#regular").slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,
            fade: true,
            asNavFor: '.variable'
        });
        $("#variable").slick({
            slidesToShow: 4,
            slidesToScroll: 1,
            asNavFor: '.regular',
            dots: false,
            centerMode: false,
            focusOnSelect: true,
            arrows:true,
        });
    };
    $('.news-section').each(function() {
        $(this).children('.news-item').matchHeight( {
            byRow: true
        });
    });
    $('.metro-portfolio .portfolio-container').isotope({
        layoutMode: 'fitRows',
        fitRows: {
            gutter: 0
        }
    });
    $('.portfolio-filter li').on('click',function() {                           
        var selector = $(this).find("a").attr('data-filter');
        $('.portfolio-filter li').removeClass('active');
        $(this).addClass('active');
        $('.metro-portfolio .portfolio-container').isotope({ filter: selector });
        return false;
    }); 
    $(function() { 
        if ( matchMedia( 'only screen and (min-width: 991px)' ).matches ) {
        }  
        portfolioIsotope();
        scrollBar();
        rangeSlider();
        prdSlider();
    });
})();