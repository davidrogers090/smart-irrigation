const Zone = ({ id, duration, enabled }) => `
		<div class="zone">
		  <div class="zoneicon">${id}</div>
		  <div class="zonetext">${timePad(Math.floor(duration / 60))}:${timePad(duration % 60)}:00</div>
		  <div class="spacer"></div>
		  <label class="switch">
		    <input type="checkbox" onclick='handleSlider(this, ${id});' ${enabled ? 'checked' : ''}>
		    <span class="slider round"></span>
		  </label>
		</div>
	`;

const ZoneStatus = (zones) => `${ zones.map(zone => `<div class="large zoneicon">${zone.id}</div>`).join('') }`;

