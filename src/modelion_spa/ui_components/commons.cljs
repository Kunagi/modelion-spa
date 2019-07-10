(ns modelion-spa.ui-components.commons
  (:require
   [re-frame.core :as rf]
   ["@material-ui/core" :as mui]))


(def spacer-size 5)
(def spacer-unit "px")


(defn deep-merge [v & vs]
  (letfn [(rec-merge [v1 v2]
            (if (and (map? v1) (map? v2))
              (merge-with deep-merge v1 v2)
              v2))]
    (when (some identity vs)
      (reduce #(rec-merge %1 %2) v vs))))


(defn spacer [count]
  (str (* count spacer-size) spacer-unit))


(defn Paperscrap
  [options content]
  [:> mui/Paper
   (deep-merge
    {:style {:padding (spacer 1)
             :margin (spacer 1)}}
    options)
   content])


(defn entity-paper-options-for-target-view
  [entity target-view]
  (if target-view
    {:style {:cursor :pointer}
     :on-click #(rf/dispatch [:modelion/activate-view
                              {:view target-view
                               :entity-id (:db/id entity)}])}
    {}))


(defn Entity-Paper
  [{:keys [entity
           target-view]}
   content]
  (let [modeling-name (or (-> entity :modeling/name)
                          (-> entity :db/id))]
    [Paperscrap
     (entity-paper-options-for-target-view entity target-view)
     [:div
      {:style {:font-weight 500}}
      modeling-name
      [:div
       content]]]))


(defn Papers-List
  [entity-component
   entities]
  (into
   [:div
    {:style {:margin "-5px"}}]
   (mapv
    (fn [entity]
      [entity-component
       entity])
    entities)))


(defn Label
  [{:keys [text]}]
  [:div
   {:style {:font-color :grey
            :font-weight :normal
            :font-style :italic}}
   (str text)])


(defn Labeled
  [{:keys [text]}
   content]
  [:div
   {:style {:padding (spacer 1)}}
   [Label
    {:text text}]
   [:div content]])




(defn Labeled-Entities-List
  [{:keys [label
           entities
           target-view]}]
  [Labeled
   {:text label}
   [Papers-List
    (fn [entity]
      [Entity-Paper
       {:entity entity
        :target-view target-view}])
    entities]])
