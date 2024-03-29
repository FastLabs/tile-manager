(ns slices.core)

;TODO: title is moving to the next line. Ideally the title should be truncated
;TODO: link the views with the entity?: each entity has a spec and data associated with it not sure if this is a good idea keep just for visualization?

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
    [:div.border.border-indigo-600.rounded-lg.shadow-md {:style {:padding 8}}
     [card-bar id title]
     [card-content content]]
    {:key id}))


(defn slice-view
  ([items]
   (slice-view {:id "slice"} items))
  ([{:keys [id vertical?]} items]
   [:div {:className (if vertical? "slice-view-vertical" "slice-view-horizontal")}
    [:<>
     ^{:key (str id "-container")}
     [:div.spacer]
     (doall
       (map-indexed (fn [index slice-element]
                      (let [m (meta slice-element)
                            k (if m (str id "-" (:key m)) (str id "-item-" index))]
                        ^{:key k} [:div.flex-grow.slice-wrapper slice-element])) items))]]))