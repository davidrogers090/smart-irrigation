const Zone = ({ pinId }) => `
		<div class="zone">
		  <div class="zoneicon">${pinId}</div>
		  <div class="zonetext">--:--:--</div>
		  <div class="spacer"></div>
		  <label class="switch">
		    <input type="checkbox">
		    <span class="slider round"></span>
		  </label>
		</div>
	`;

const ZoneStatus = (zones) => `${ zones.map(zone => `<div class="${zone.active ? 'activeicon' : ''} large zoneicon">${zone.pinId}</div>`).join('') }`;

