import { useEffect, useState } from 'react';
import {Sector} from "@/app/types/types";

interface SectorSelectProps {
  sectors: Sector[];
  selected: number[];
  onChange: (selected: number[]) => void;
}

export const SectorSelect = ({ sectors, selected, onChange }: SectorSelectProps) => {
  const [flatSectors, setFlatSectors] = useState<Sector[]>([]);
  useEffect(() => {
    const flatten = (items: Sector[], level = 0): Sector[] =>
      items.flatMap(item => [
        { ...item, level },
        ...flatten(item.children || [], level + 1)
      ]);
    setFlatSectors(flatten(sectors));
  }, [sectors]);

  return (
    <select
      multiple
      className="w-full h-64 border rounded p-2"
      value={selected.map(String)}
      onChange={e =>
        onChange(Array.from(e.target.selectedOptions).map(o => parseInt(o.value)))
      }
    >
      {flatSectors.map(sector => (
        <option
          key={sector.id}
          value={sector.id}
          style={{ paddingLeft: `${(sector.level ?? 0) * 20}px` }}
          className="py-1 hover:bg-gray-100"
        >
          {sector.name}
        </option>
      ))}
    </select>
  );
};
