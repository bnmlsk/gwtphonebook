/*
 * Copyright (C) 2015 Liza Lukicheva
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.bananamilkshake.dispatcher;

import net.customware.gwt.dispatch.shared.Result;

public class EditCardResult implements Result {
	private String oldName;
	private String oldPhone;
	
	private int cardId;
	
	private String setName;
	private String setPhone;
	
	public EditCardResult() {
	}
	
	public EditCardResult(String oldName, String oldPhone, int cardId, String setName, String setPhone) {
		this.oldName = oldName;
		this.oldPhone = oldPhone;
		
		this.cardId = cardId;
		
		this.setName = setName;
		this.setPhone = setPhone;
	}
	
	/**
	 * @return Name value that was been changed.
	 */
	public String getOldName() {
		return this.oldName;
	}
	
	/**
	 * @return Phone value that was been changed
	 */
	public String getOldPhone() {
		return this.oldPhone;
	}
	
	/** 
	 * @return Set card id
	 */
	public int getId() {
		return this.cardId;
	}
	
	/**
	 * @return Name value that was set instead of old value
	 */
	public String getSetName() {
		return this.setName;
	}
	
	/**
	 * @return Phone value that was set instead of old value
	 */
	public String getSetPhone() {
		return this.setPhone;
	}
}
