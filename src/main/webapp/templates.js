const Zone = ({ pinId, duration }) => `
		<div class="zone">
		  <div class="zoneicon">${pinId}</div>
		  <div class="zonetext">${timePad(Math.floor(duration / 60))}:${timePad(duration % 60)}:00</div>
		  <div class="spacer"></div>
		  <label class="switch">
		    <input type="checkbox">
		    <span class="slider round"></span>
		  </label>
		</div>
	`;

const ZoneStatus = (zones) => `${ zones.map(zone => `<div class="large zoneicon">${zone.pinId}</div>`).join('') }`;

