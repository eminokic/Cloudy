$(document).ready(function() {
    var lat;
    var long;

    if(navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            lat = position.coords.latitude;
            long = position.coords.longitude;

            var api = 'https://fcc-weather-api.glitch.me/api/current?lat='+lat+'&lon='+long;

            $.getJSON(api, function(res) {
                var celsius = res.main.temp;
                var farenheit = (celsius*1.8) + 32;

                var location = res.name;

                $('.weather-location').html(location);
                $('.temp').html(Math.floor(celsius));
                $('.weather-description').html(res.weather[0].description);
                $('.weatherType').attr('id', res.weather[0].main);
                $('.row2').on('click', function () {
                    if ($('.temp').html() == (Math.floor(celsius))) {
                        $('.temp').html(Math.floor(farenheit));
                        $('.temp-type').html('°F');

                    } else {
                        $('.temp').html(Math.floor(celsius));
                        $('.temp-type').html('°C');
                    }
                });

                var icons = new Skycons({
                    "color": "white"
                });

                icons.set("Clear-day", Skycons.CLEAR_DAY);
                icons.set("Clear-night", Skycons.CLEAR_NIGHT);
                icons.set("Partly-cloudy-day", Skycons.PARTLY_CLOUDY_DAY);
                icons.set("Partly-cloudy-night", Skycons.PARTLY_CLOUDY_NIGHT);
                icons.set("Clouds", Skycons.CLOUDY);
                icons.set("Rain", Skycons.RAIN);
                icons.set("Sleet", Skycons.SLEET);
                icons.set("Snow", Skycons.SNOW);
                icons.set("Wind", Skycons.WIND);
                icons.set("Fog", Skycons.FOG);
                icons.play();

            });
        });
    }
});

function isOverflown({
    clientWidth, clientHeight, scrollWidth, scrollHeight
  }) {
    return scrollHeight > clientHeight || scrollWidth > clientWidth;
  }
  
  function isVisible(parent, child) {
    return !(
      (child.offsetLeft - parent.offsetLeft >= parent.offsetWidth)
      || (child.offsetTop - parent.offsetTop >= parent.offsetHeight)
    );
  }
  
  function init() {
    const page = document.querySelector('[data-main-page]');
    const header = document.querySelector('[data-header]');
    const topbar = document.querySelector('[data-topbar]');
    const nav = header.querySelector('[data-nav]');
    const navItems = nav.querySelectorAll('[data-nav-item]');
    const mobileNavList = document.querySelector('[data-mobile-nav-list]');
    const mobileNavItems = document.querySelectorAll('[data-mobile-nav-item]');
    const mobileNavTriggers = document.querySelectorAll('[data-mobile-nav-trigger]');
    const mobileNavOverlay = document.querySelector('[data-mobile-nav-overlay]');
  
    // Resize Observer checking whether to show mobile nav button based on if a nav element is overflowing
    const showMobileNavButton = () => {
      const navHidden = getComputedStyle(nav, null).display === 'none';
      if (navHidden) {
        mobileNavItems.forEach((item) => {
          item.classList.add('is-visible');
        });
      }
  
      const resizeObserver = new ResizeObserver((entries) => {
        for (const entry of entries) {
          header.classList.toggle('has-mobile-button', isOverflown(nav));
          navItems.forEach((item) => {
            const navItems = Array.from(mobileNavItems);
            const matchingNavItem = navItems.find(el => el.dataset.mobileNavItem === item.dataset.navItem);
  
            matchingNavItem.classList.toggle('is-visible', !isVisible(nav, item));
          });
        }
      });
  
      resizeObserver.observe(nav);
    };
  
    // Mobile nav button open/close
    mobileNavTriggers.forEach((trigger) => {
      trigger.addEventListener('click', () => {
        mobileNavTriggers.forEach((trigger) => trigger.classList.toggle('is-active'));
        document.body.classList.toggle('is-mobilenav-open');
      });
    });
}

function isOverflown({
    clientWidth, clientHeight, scrollWidth, scrollHeight
  }) {
    return scrollHeight > clientHeight || scrollWidth > clientWidth;
  }
  
  function isVisible(parent, child) {
    return !(
      (child.offsetLeft - parent.offsetLeft >= parent.offsetWidth)
      || (child.offsetTop - parent.offsetTop >= parent.offsetHeight)
    );
  }
  
  function init() {
    const page = document.querySelector('[data-main-page]');
    const header = document.querySelector('[data-header]');
    const topbar = document.querySelector('[data-topbar]');
    const nav = header.querySelector('[data-nav]');
    const navItems = nav.querySelectorAll('[data-nav-item]');
    const mobileNavList = document.querySelector('[data-mobile-nav-list]');
    const mobileNavItems = document.querySelectorAll('[data-mobile-nav-item]');
    const mobileNavTriggers = document.querySelectorAll('[data-mobile-nav-trigger]');
    const mobileNavOverlay = document.querySelector('[data-mobile-nav-overlay]');
  
    // Resize Observer checking whether to show mobile nav button based on if a nav element is overflowing
    const showMobileNavButton = () => {
      const navHidden = getComputedStyle(nav, null).display === 'none';
      if (navHidden) {
        mobileNavItems.forEach((item) => {
          item.classList.add('is-visible');
        });
      }
  
      const resizeObserver = new ResizeObserver((entries) => {
        for (const entry of entries) {
          header.classList.toggle('has-mobile-button', isOverflown(nav));
          navItems.forEach((item) => {
            const navItems = Array.from(mobileNavItems);
            const matchingNavItem = navItems.find(el => el.dataset.mobileNavItem === item.dataset.navItem);
  
            matchingNavItem.classList.toggle('is-visible', !isVisible(nav, item));
          });
        }
      });
  
      resizeObserver.observe(nav);
    };
  
    // Mobile nav button open/close
    mobileNavTriggers.forEach((trigger) => {
      trigger.addEventListener('click', () => {
        mobileNavTriggers.forEach((trigger) => trigger.classList.toggle('is-active'));
        document.body.classList.toggle('is-mobilenav-open');
      });
    });
  
    // Mobile nav overlay close
    mobileNavOverlay.addEventListener('click', () => {
      mobileNavTriggers.forEach((trigger) => {
        trigger.classList.remove('is-active');
      });
      document.body.classList.remove('is-mobilenav-open');
    });
  
    // Mobile nav accordion functionality
    mobileNavItems.forEach((item) => {
      const trigger = item.querySelector('[data-mobile-nav-item-trigger]');
      if (trigger) {
        trigger.addEventListener('click', () => {
          const openSubnav = mobileNavList.querySelector('.is-subnav-open');
  
          if (openSubnav) {
            if (openSubnav !== item) {
              openSubnav.classList.remove('is-subnav-open');
              item.classList.add('is-subnav-open');
            } else {
              item.classList.remove('is-subnav-open');
              mobileNavList.classList.remove('is-subnav-open');
            }
          } else {
            item.classList.add('is-subnav-open');
            mobileNavList.classList.add('is-subnav-open');
          }
        });
      }
    });
  
    showMobileNavButton();
  }
  
  init();