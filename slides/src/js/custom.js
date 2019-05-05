let defaultCss = 'css/style.css'
let talendCss = 'css/talend.css'

Reveal.addEventListener( 'dynamic', function() {

  const html = `
    <h1>Dynamic slide</h1>
    <p>My paragraph</p>
    <p id="myparagraph"><i>Choose a color</i></p>
    <p>
        <button onclick="document.getElementById('theme').setAttribute('href','css/talend.css')">Talend</button>
        <button onclick="document.getElementsByTagName('body')[0].style.background ='red'">Red</button>
        <button onclick="document.getElementsByTagName('body')[0].style.background ='blue'">Blue</button>
    </p>
  `;

  document.getElementById('slide_seven_js').innerHTML = html;
}, false );

Reveal.configure({
  keyboard: {
    67: function() { // change css on C
      const theme = document.getElementById('theme')
      if ( defaultCss === theme.getAttribute('href')) {
        console.log("talend")
        theme.setAttribute('href',talendCss)
      } else {
        console.log("default")
        theme.setAttribute('href',defaultCss)
      }
    },
  }
});
