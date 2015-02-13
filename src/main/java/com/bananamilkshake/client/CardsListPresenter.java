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

package com.bananamilkshake.client;

import com.bananamilkshake.shared.Card;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

public class CardsListPresenter extends WidgetPresenter<CardsListPresenter.Display> {
	
	public static final Place PLACE = new Place("list");
	
	public static final String PARAM_ALL = "all";
	public static final String PARAM_PATTERN = "pattern";
	
	private final PhoneBookServiceAsync service = GWT.create(PhoneBookService.class);
	
	public interface Display extends WidgetDisplay {
		VerticalPanel getPanel();
	}
	
	public CardsListPresenter(Display display, EventBus eventBus) {
		super(display, eventBus);
	}
	
	@Override
	protected void onBind() {
	}

	@Override
	protected void onUnbind() {
	}

	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Override
	protected void onPlaceRequest(PlaceRequest request) {
		String paramAllValue = request.getParameter(PARAM_ALL, Boolean.toString(true));
		if (Boolean.valueOf(paramAllValue)) {
			this.service.showAll(new CardsListAsyncCallback());
		} else {
			String pattern = request.getParameter(PARAM_PATTERN, null);
			this.service.search(pattern, new CardsListAsyncCallback());
		}
	}

	@Override
	public void refreshDisplay() {
	}

	@Override
	public void revealDisplay() {
		RootPanel.get().add(this.display.asWidget());
	}

	private void showCards(ArrayList<Card> cards) {
		this.display.getPanel().clear();
		
		for (Card card : cards) {			
			PlaceRequest linkTo = CardPresenter.PLACE.requestWith(CardPresenter.PARAM_ID, String.valueOf(card.getId()));
			
			String cardStr = card.getName() + ": " + card.getPhone();
			
			Hyperlink cardLink = new Hyperlink(cardStr, linkTo.toString());
			
			this.display.getPanel().add(cardLink);
		}
	}
	
	private class CardsListAsyncCallback implements AsyncCallback<ArrayList<Card>> {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(ArrayList<Card> cards) {
			CardsListPresenter.this.showCards(cards);
		}
	}
}
