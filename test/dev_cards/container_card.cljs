(ns dev-cards.container-card
  (:require [reagent.core :as ra]
            [devcards.core :refer-macros [defcard-rg defcard]]))

;TODO: title is moving to the next line. Ideally the title should be truncated
;TODO: link the views with the entity: each entity has a spec and data associated with it

(defn fa-icon
  ([name]
   (fa-icon name {}))
  ([name _opts]
   [:i.fas {:className name
            :style     {:font-size 14}}]))


(defn action-bar
  ([actions {:keys [action-handler-fn] :or {action-handler-fn (fn [_])}}]
   [:ul
    [:<>
     (doall
       (for [{:keys [action-id title icon]} actions]
         ^{:key action-id} [:li {:title    title
                                 :on-click (fn [_] (action-handler-fn action-id))
                                 :style    {:cursor :pointer}} [fa-icon icon]]))]]))

(defn card-bar [id title]
  [:div.border-b-2.border-gray-100
   [:div.flex.flex-row.flex-no-wrap.gap-4.justify-end
    [:div.w-full
     [:span title]]
    [action-bar [{:action-id :right-expand
                  :title     "Expand Right"
                  :icon      "fa-arrow-right"}]
     {:action-handler-fn (fn [action] (prn action id))}]]])

(defn card-content [content]
  [:div content])

(defn view-card [{:keys [id title content] :as view-spec}]
  (with-meta
    [:div.border-2.border-indigo-600.rounded-lg {:style {:padding 8}}
     [card-bar id title]
     [card-content content]]
    {:key id}))


(defn slice [items]
  [:div.flex.flex-row.space-x-2
   [:<>
    ^{:key "slice-controller"} [:div.order-first {:style {:width            10
                                                          :background-color :darkgrey}}]
    (doall
      (for [i items]
        (do
          (prn (meta i))
          (with-meta [:div.flex-grow i] (meta i)))))]])


(defcard-rg
  container-vew
  [:div
   [view-card {:id                "main-card"
               :title             "main card"
               :content           [:div "main card content"]
               :action-handler-fn (fn [action view-id])
               :actions           [{:action :close
                                    :icon   ""}]}]])


(defcard-rg
  slice-view
  [:div.container.mx-auto
   [slice
    [^{:key "first-card"} [view-card {:id      "first-card"
                                      :title   "Simple View"
                                      :content [:div "Simple View Content"]}]
     ^{:key "second-card"} [view-card {:id      "second-card"
                                       :title   "Not A simple view"
                                       :content [:div "Another view"]}]]]])

